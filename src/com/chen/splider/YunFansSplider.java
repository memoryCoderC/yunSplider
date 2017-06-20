package com.chen.splider;

import com.chen.dao.FansDao;
import com.chen.entity.FansInfo;
import com.chen.exception.CanNotConvertJsonToObjException;
import com.chen.exception.GetReponseObjExceoption;
import com.chen.exception.NetStateNotOKException;
import com.chen.parser.FansParser;
import com.chen.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chen on 2017/6/14.
 */
public class YunFansSplider implements Runnable {

    private SpliderCore spliderCore;//核心爬取类
    private Logger logger = LoggerFactory.getLogger(YunFansSplider.class);
    private String fansUrl = null;
    private FansDao fansDao = new FansDao();
    volatile boolean isRun = true;

    public YunFansSplider() {
        this(new SpliderCore(), true);
    }

    public YunFansSplider(SpliderCore spliderCore) {
        this(spliderCore, true);
    }

    public YunFansSplider(boolean isRun) {
        this(new SpliderCore(), isRun);
    }

    public YunFansSplider(String fansUrl, boolean isRun) {
        this(fansUrl, new SpliderCore(), isRun);
    }

    public YunFansSplider(SpliderCore spliderCore, boolean isRun) {
        this(PropertiesUtil.getFansUrl(), spliderCore, isRun);
    }

    public YunFansSplider(String fansUrl, SpliderCore spliderCore, boolean isRun) {
        this.fansUrl = fansUrl;
        this.spliderCore = spliderCore;
        this.isRun = isRun;
    }


    public boolean getFans(String uk) {
        //如果uk已经被爬取就不需要爬取（数据库实现)

        FansParser paser = new FansParser();
        String resultPage = "";//爬取的结果
        int currentPage = 0;//当前分页
        int totalPage = 0;///总页数

        do {
            try {
                String real_url = String.format(fansUrl, uk, currentPage * 24);//构造真实路径


                while (true) {
                    //开始爬取
                    logger.info("爬取开始-----uk" + uk + "start:" + currentPage * 24);
                    resultPage = spliderCore.doGet(real_url);
                    logger.info("爬取结束-----uk" + uk + "start:" + currentPage * 24);
                    if (!resultPage.startsWith("{\"errno\":-55")) {
                        break;
                    }
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                //为空不需要解析
                if (resultPage == null || resultPage.equals("")) {
                    return true;
                }
                //开始解析
                logger.info("解析开始-----uk" + uk + "start:" + currentPage * 24);
                List<FansInfo> fansInfos = paser.parseFansInfo(resultPage);
                logger.info("解析结束-----uk" + uk + "start:" + currentPage * 24);


                //保存到数据库
                logger.info("存入数据库开始-----uk" + uk + "start:" + currentPage * 24);
                for (FansInfo fansInfo : fansInfos) {
                    try {
                        fansDao.saveFans(fansInfo);
                    } catch (SQLException e) {
                        logger.error("存入数据库错误-----uk" + uk + "错误fans" + fansInfo.getFans_uk() + e.getMessage());
                        e.printStackTrace();
                    }
                }
                logger.info("存入数据库结束-----uk" + uk + "start:" + currentPage * 24);

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
                return false;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentPage++;
        } while (currentPage < totalPage);
        return true;
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
                ukList = fansDao.getUkList(FansDao.COLUMN_FANS_CRAW);
                for (String s : ukList) {
                    if (getFans(s))
                        fansDao.updateClaw(FansDao.COLUMN_FANS_CRAW, s);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
