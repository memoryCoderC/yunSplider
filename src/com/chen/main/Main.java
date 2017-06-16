package com.chen.main;

import com.chen.splider.YunFansSplider;

/**
 * Created by chen on 2017/6/14.
 */
public class Main {
    public static void main(String[] args) {
        //YunShareSplider shareSplider = new YunShareSplider();
        //shareSplider.getShare("855888949");
        YunFansSplider userSplider = new YunFansSplider();
        userSplider.getFans("855888949");
        //YunFollowSplider followSplider = new YunFollowSplider();
        //followSplider.getFollow("855888949");
    }
}
