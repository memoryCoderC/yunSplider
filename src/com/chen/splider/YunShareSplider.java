package com.chen.splider;

import com.chen.dao.FileDao;
import com.chen.dao.ShareDao;
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
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2017/6/15.
 * 爬取分享列表
 */
public class YunShareSplider {
    private SpliderCore spliderCore;//核心爬取类
    private Logger logger = LoggerFactory.getLogger(YunFansSplider.class);
    private String shareUrl = null;
    private ShareDao shareDao = new ShareDao();
    private FileDao fileDao = new FileDao();
    /**
     *
     */
    public YunShareSplider() {
        this(new SpliderCore());
    }

    public YunShareSplider(String shareUrl) {
        this(shareUrl, new SpliderCore());
    }

    public YunShareSplider(SpliderCore spliderCore) {
        this(PropertiesUtil.getShareUrl(), spliderCore);
    }

    public YunShareSplider(String shareUrl, SpliderCore spliderCore) {
        this.shareUrl = shareUrl;
        this.spliderCore = spliderCore;
    }


    public boolean getShare(String uk, String url) {
        //如果uk已经被爬取就不需要爬取（数据库实现)


        String resultPage = "";//爬取的结果
        int currentPage = 0;//当前分页
        int totalPage = 0;///总页数

        //设置爬取头信息
        Map map = new HashMap();
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
        map.put("X-Requested-With", "XMLHttpRequest");
        map.put("Accept", "application/json, text/javascript, */*; q=0.01");
        map.put("Referer", "https://yun.baidu.com/share/home?uk=325913312#category/type=0");
        map.put("Accept-Language", "zh-CN");

        do {
            try {
                String real_url = String.format(url, currentPage * 24, uk);//构造真实路径

                //开始爬取
                logger.info("爬取开始-----uk" + uk + "start:" + currentPage * 24);
                resultPage = spliderCore.doGet(real_url, map);
                logger.info("爬取结束-----uk" + uk + "start:" + currentPage * 24);

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
            List<ShareInfo> shareInfos;
            SharePaser paser = new SharePaser();
            logger.info("解析开始-----uk" + uk + "start:" + currentPage * 24);
            try {
                totalPage = paser.getTotalCount(resultPage);
                shareInfos = paser.parseShareInfo(resultPage);
            } catch (CanNotConvertJsonToObjException e) {
                logger.error(e.toString());
                e.printStackTrace();
                return true;
            }

            for (ShareInfo shareInfo : shareInfos) {
                try {
                    shareDao.saveShare(shareInfo);
                    for (FileInfo fileInfo : shareInfo.getFilelist()) {
                        fileInfo.setShorturl(shareInfo.getShorturl());
                        fileDao.saveFile(fileInfo);
                    }
                } catch (SQLException e) {
                    logger.error("保存数据库出错");
                    e.printStackTrace();
                }
            }

            logger.info("解析结束-----uk" + uk + "start:" + currentPage * 24);
            currentPage++;
        } while (currentPage < totalPage);
        return true;
    }

    public boolean getShare(String uk) {
        return getShare(uk, shareUrl);
    }

}
