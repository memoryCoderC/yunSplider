package com.chen.splider;

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
import java.util.List;

/**
 * Created by chen on 2017/6/14.
 */
public class YunFollowSplider {

    private SpliderCore spliderCore;//核心爬取类
    private Logger logger = LoggerFactory.getLogger(YunFollowSplider.class);
    private String followUrl = null;
    private FollowDao followDao = new FollowDao();


    public YunFollowSplider() {
        this(new SpliderCore());
    }

    public YunFollowSplider(String followUrl) {
        this(followUrl, new SpliderCore());
    }

    public YunFollowSplider(SpliderCore spliderCore) {
        this(PropertiesUtil.getFollowUrl(), spliderCore);
    }

    public YunFollowSplider(String followUrl, SpliderCore spliderCore) {
        this.followUrl = followUrl;
        this.spliderCore = spliderCore;
    }


    public boolean getFollow(String uk, String url) {
        //如果uk已经被爬取就不需要爬取（数据库实现)

        FollowParser paser = new FollowParser();
        String resultPage = "";//爬取的结果
        int currentPage = 0;//当前分页
        int totalPage = 0;///总页数

        do {
            try {
                String real_url = String.format(url, uk, currentPage * 24);//构造真实路径

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
                List<FollowInfo> followInfos = paser.parseFollowInfo(resultPage);
                logger.info("解析结束-----uk" + uk + "start:" + currentPage * 24);

                for (FollowInfo followInfo : followInfos) {
                    try {
                        followDao.saveFollow(followInfo);
                    } catch (SQLException e) {
                        logger.error("存入数据库错误-----uk"+uk+"错误Follow"+ followInfo.getFollow_uk());
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
        } while (currentPage < totalPage);
        return true;
    }

    public boolean getFollow(String uk) {
        return getFollow(uk, followUrl);
    }

}
