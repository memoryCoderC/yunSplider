package com.chen.splider;

import com.chen.dao.FansDao;
import com.chen.dao.FollowDao;
import com.chen.entity.FollowInfo;
import com.chen.exception.CanNotConvertJsonToObjException;
import com.chen.exception.GetReponseObjExceoption;
import com.chen.exception.NetStateNotOKException;
import com.chen.parser.FollowParser;
import com.chen.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chen on 2017/6/14.
 */
public class YunFollowSplider implements Runnable {

    private SpliderCore spliderCore;//核心爬取类
    private Logger logger = LoggerFactory.getLogger(YunFollowSplider.class);
    private String followUrl = null;
    private FollowDao followDao = new FollowDao();
    private FansDao fansDao = new FansDao();
    volatile boolean isRun;

    public YunFollowSplider() {
        this(new SpliderCore());
    }

    public YunFollowSplider(String followUrl, boolean isRun) {
        this(followUrl, new SpliderCore(), isRun);
    }

    public YunFollowSplider(SpliderCore spliderCore) {
        this(PropertiesUtil.getFollowUrl(), spliderCore, true);
    }

    public YunFollowSplider(SpliderCore spliderCore, boolean isRun) {
        this(PropertiesUtil.getFollowUrl(), spliderCore, isRun);
    }

    public YunFollowSplider(String followUrl, SpliderCore spliderCore, boolean isRun) {
        this.followUrl = followUrl;
        this.spliderCore = spliderCore;
        this.isRun = isRun;
    }


    public boolean getFollow(String uk) {
        //如果uk已经被爬取就不需要爬取（数据库实现)

        FollowParser paser = new FollowParser();
        String resultPage = "";//爬取的结果
        int currentPage = 0;//当前分页
        int totalPage = 0;///总页数

        do {
            try {
                String real_url = String.format(followUrl, uk, currentPage * 24);//构造真实路径

                while (true) {
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
                List<FollowInfo> followInfos = paser.parseFollowInfo(resultPage);
                logger.info("解析结束-----uk" + uk + "start:" + currentPage * 24);

                for (FollowInfo followInfo : followInfos) {
                    try {
                        followDao.saveFollow(followInfo);
                    } catch (SQLException e) {
                        logger.error("存入数据库错误-----uk" + uk + "错误Follow" + followInfo.getFollow_uk());
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
            }

            currentPage++;
            try {
                Thread.sleep(3000);
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
                for (String s : ukList) {
                    if (getFollow(s))
                        fansDao.updateClaw(FansDao.COLUMN_FOLLOW_CRAW, s);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
