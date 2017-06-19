package com.chen.splider;

import com.chen.dao.AlbumDao;
import com.chen.dao.FansDao;
import com.chen.entity.AlbumInfo;
import com.chen.exception.CanNotConvertJsonToObjException;
import com.chen.exception.GetReponseObjExceoption;
import com.chen.exception.NetStateNotOKException;
import com.chen.parser.AlbumParser;
import com.chen.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chen on 2017/6/14.
 */
public class YunAlbumSplider implements Runnable {

    private SpliderCore spliderCore;//核心爬取类
    private Logger logger = LoggerFactory.getLogger(YunAlbumSplider.class);
    private String albumUrl = null;
    private AlbumDao albumDao = new AlbumDao();
    private FansDao fansDao = new FansDao();
    volatile boolean isRun;

    public YunAlbumSplider() {
        this(new SpliderCore());
    }

    public YunAlbumSplider(String followUrl, boolean isRun) {
        this(followUrl, new SpliderCore(), isRun);
    }

    public YunAlbumSplider(SpliderCore spliderCore) {
        this(PropertiesUtil.getAlbumUrl(), spliderCore, true);
    }

    public YunAlbumSplider(SpliderCore spliderCore, boolean isRun) {
        this(PropertiesUtil.getAlbumUrl(), spliderCore, isRun);
    }

    public YunAlbumSplider(String followUrl, SpliderCore spliderCore, boolean isRun) {
        this.albumUrl = followUrl;
        this.spliderCore = spliderCore;
        this.isRun = isRun;
    }


    public boolean getAlbum(String uk) {
        //如果uk已经被爬取就不需要爬取（数据库实现)

        AlbumParser paser = new AlbumParser();
        String resultPage = "";//爬取的结果
        int currentPage = 0;//当前分页
        int totalPage = 0;///总页数

        do {
            try {
                String real_url = String.format(albumUrl, uk, currentPage * 24);//构造真实路径

                //开始爬取
                logger.info("爬取开始-----uk" + uk + "start:" + currentPage * 24);
                resultPage = spliderCore.doGet(real_url);
                logger.info("爬取结束-----uk" + uk + "start:" + currentPage * 24);

                //为空不需要解析
                if (resultPage == null || resultPage.equals("")) {
                    return true;
                }
                //开始解析
                logger.info("解析开始-----uk" + uk + "start:" + currentPage * 24);
                List<AlbumInfo> followInfos = paser.parseAlbumInfo(resultPage);
                logger.info("解析结束-----uk" + uk + "start:" + currentPage * 24);

                for (AlbumInfo albumInfo : followInfos) {
                    try {
                        albumInfo.setUk(uk);
                        albumDao.saveAlbum(albumInfo);
                    } catch (SQLException e) {
                        logger.error("存入数据库错误-----uk" + uk + "错误id" + albumInfo.getAlbum_id());
                        e.printStackTrace();
                    }
                }
                totalPage = paser.getTotalCount(resultPage) / 24;//获取总页数

            } catch (NetStateNotOKException e) {
                logger.error(e.toString());
                e.printStackTrace();
                return false;
            } catch (GetReponseObjExceoption e) {
                logger.error(e.toString());
                e.printStackTrace();
                return false;
            } catch (CanNotConvertJsonToObjException e) {
                e.printStackTrace();
                logger.error(e.toString());
                return true;
            }

            currentPage++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (currentPage < totalPage);
        return true;
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        List<String> ukList = new LinkedList<>();

        while (isRun) {
            try {
                ukList = fansDao.getUkList(FansDao.COLUMN_FOLLOW_CRAW);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (String s : ukList) {
                getAlbum(s);
            }
        }
    }
}
