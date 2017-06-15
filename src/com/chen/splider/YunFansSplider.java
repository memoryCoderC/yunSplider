package com.chen.splider;

import com.chen.entity.FansInfo;
import com.chen.exception.CanNotConvertJsonToObjException;
import com.chen.exception.GetReponseObjExceoption;
import com.chen.exception.NetStateNotOKException;
import com.chen.parser.FansParser;
import com.chen.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by chen on 2017/6/14.
 */
public class YunFansSplider {

    private SpliderCore spliderCore;//核心爬取类
    private Logger logger = LoggerFactory.getLogger(YunFansSplider.class);
    private String fansUrl = null;

    public YunFansSplider() {
        this(new SpliderCore());
    }

    public YunFansSplider(String fansUrl) {
        this(fansUrl, new SpliderCore());
    }

    public YunFansSplider(SpliderCore spliderCore) {
        this(PropertiesUtil.getFansUrl(), spliderCore);
    }

    public YunFansSplider(String fansUrl, SpliderCore spliderCore) {
        this.fansUrl = fansUrl;
        this.spliderCore = spliderCore;
    }


    public boolean getFans(String uk, String url) {
        //如果uk已经被爬取就不需要爬取（数据库实现)

        FansParser paser = new FansParser();
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
                    return false;
                }
                //开始解析
                logger.info("解析开始-----uk" + uk + "start:" + currentPage * 24);
                List<FansInfo> fansInfos = paser.parseFansInfo(resultPage);
                logger.info("解析结束-----uk" + uk + "start:" + currentPage * 24);

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

            currentPage++;
        } while (currentPage < totalPage);
        return true;
    }

    public boolean getFans(String uk) {
        return getFans(uk, fansUrl);
    }

}