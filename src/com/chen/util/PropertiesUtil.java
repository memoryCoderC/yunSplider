package com.chen.util;

import java.util.ResourceBundle;

/**
 * Created by chen on 2017/6/14.
 */
public class PropertiesUtil {
    private static ResourceBundle bundle = ResourceBundle.getBundle("myProp");//读取

    public static String getFollowUrl() {
        return bundle.getString("followUrl");
    }

    public static String getFansUrl() {
        return bundle.getString("fansUrl");
    }

    public static String getShareUrl() {
        return bundle.getString("shareUrl");
    }
}
