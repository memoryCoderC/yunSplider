package com.chen.splider;

import com.chen.dao.FansDao;
import com.chen.dao.FileInfoDao;
import com.chen.entity.FileInfo;
import com.chen.entity.ShareInfo;
import com.chen.exception.CanNotConvertJsonToObjException;
import com.chen.exception.GetReponseObjExceoption;
import com.chen.exception.NetStateNotOKException;
import com.chen.parser.SharePaser;
import com.chen.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2017/6/15.
 * 爬取分享列表
 */
public class YunShareSplider implements Runnable {
    private SpliderCore spliderCore;//核心爬取类
    private Logger logger = LoggerFactory.getLogger(YunFansSplider.class);
    private String shareUrl = null;
    private FileInfoDao fileInfoDao = new FileInfoDao();
    volatile boolean isRun;
    private FansDao fansDao = new FansDao();

    /**
     *
     */
    public YunShareSplider() {
        this(new SpliderCore(), true);
    }

    public YunShareSplider(boolean isRun) {
        this(new SpliderCore(), isRun);
    }

    public YunShareSplider(String shareUrl, boolean isRun) {
        this(shareUrl, new SpliderCore(), isRun);
    }

    public YunShareSplider(SpliderCore spliderCore) {
        this(PropertiesUtil.getShareUrl(), spliderCore, true);
    }

    public YunShareSplider(SpliderCore spliderCore, boolean isRun) {
        this(PropertiesUtil.getShareUrl(), spliderCore, isRun);
    }

    public YunShareSplider(String shareUrl, SpliderCore spliderCore, boolean isRun) {
        this.shareUrl = shareUrl;
        this.spliderCore = spliderCore;
        this.isRun = isRun;
    }


    public boolean getShare(String uk) {
        //如果uk已经被爬取就不需要爬取（数据库实现)


        String resultPage = "";//爬取的结果
        int currentPage = 0;//当前分页
        int totalPage = 0;///总页数

        Map map = new HashMap();
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
        map.put("X-Requested-With", "XMLHttpRequest");
        map.put("Accept", "application/json, text/javascript, */*; q=0.01");
        map.put("Referer", "http://pan.baidu.com/share/home?uk=743889484&view=share");
        map.put("Accept-Language", "zh-CN");
        do {
            try {
                String real_url = String.format(shareUrl, currentPage * 24, uk);//构造真实路径
                while (true) {
                    //开始爬取
                    logger.info("爬取开始-----uk" + uk + "start:" + currentPage * 24);
                    resultPage = spliderCore.doGet(real_url, map);
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

            } catch (NetStateNotOKException e) {
                logger.error(e.toString());
                e.printStackTrace();
                return false;
            } catch (GetReponseObjExceoption e) {
                logger.error(e.toString());
                e.printStackTrace();
                return false;
            }
            List<ShareInfo> shareInfos = null;
            SharePaser paser = new SharePaser();
            logger.info("解析开始-----uk" + uk + "start:" + currentPage * 24);
            try {
                totalPage = paser.getTotalCount(resultPage) / 24;
                shareInfos = paser.parseShareInfo(resultPage);
            } catch (CanNotConvertJsonToObjException e) {
                logger.error(e.toString());
                e.printStackTrace();
            }

            for (ShareInfo shareInfo : shareInfos) {
                try {
                    for (FileInfo fileInfo : shareInfo.getFilelist()) {
                        fileInfo.setShareid(shareInfo.getShareid());
                        fileInfo.setUk(shareInfo.getUk());
                        fileInfoDao.saveFile(fileInfo);
                    }
                } catch (SQLException e) {
                    logger.error("保存数据库出错");
                    e.printStackTrace();
                }
            }
            logger.info("解析结束-----uk" + uk + "start:" + currentPage * 24);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentPage++;
        } while (currentPage < totalPage);
        return true;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }


    public boolean getIsRun() {
        return isRun;
    }

    public void setIsRun(boolean isRun) {
        this.isRun = isRun;
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
                ukList = fansDao.getUkList(FansDao.COLUMN_PUBSHARE_CRAW);
                for (String s : ukList) {
                    if (getShare(s))
                        fansDao.updateClaw(FansDao.COLUMN_PUBSHARE_CRAW, s);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
