package com.chen.main;

import com.chen.splider.*;

/**
 * Created by chen on 2017/6/14.
 */
public class Main {
    public static void main(String[] args) {
        SpliderCore spliderCore = new SpliderCore();

        new Thread(new YunShareSplider(spliderCore)).start();
        new Thread(new YunFansSplider(spliderCore)).start();
        new Thread(new YunFollowSplider(spliderCore)).start();
        new Thread(new YunAlbumSplider(spliderCore)).start();
    }
}
