package com.chen.main;

import com.chen.splider.YunShareSplider;

/**
 * Created by chen on 2017/6/14.
 */
public class Mian {
    public static void main(String[] args) {
        YunShareSplider shareSplider = new YunShareSplider();
        shareSplider.getShare("224534490");
    }
}
