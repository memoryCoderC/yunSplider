package com.chen.main;

import com.chen.splider.SpliderCore;
import com.chen.splider.YunAlbumSplider;
import com.chen.splider.YunFollowSplider;
import com.chen.splider.YunShareSplider;

/**
 * Created by chen on 2017/6/14.
 */
public class Main {
    public static void main(String[] args) {
        SpliderCore spliderCore = new SpliderCore();

        new Thread(new YunShareSplider(spliderCore)).start();
        //new Thread(new YunFansSplider(spliderCore)).start();
        new Thread(new YunFollowSplider(spliderCore)).start();
        new Thread(new YunAlbumSplider(spliderCore)).start();
    }
}
