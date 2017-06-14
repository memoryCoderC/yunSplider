package com.chen.splider;

import com.chen.entity.UserInfo;
import com.chen.exception.GetReponseObjExceoption;
import com.chen.exception.NetStateNotOKException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by chen on 2017/6/14.
 */
public class YunSplider {

    private SpliderCore spliderCore = new SpliderCore();//核心爬取类
    private Logger logger = new Logger();



    public List<UserInfo> getFans(String uk, String start) {
        String url = "http://pan.baidu.com/pcloud/friend/getfanslist?query_uk=" + uk + "&limit=24&start=" + start;
        try {
            String s = spliderCore.doGet(url);
        } catch (NetStateNotOKException e) {
            e.printStackTrace();
        } catch (GetReponseObjExceoption getReponseObjExceoption) {
            getReponseObjExceoption.printStackTrace();
        }
    }
}
