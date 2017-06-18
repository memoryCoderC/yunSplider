package com.chen.main;

import com.chen.dao.FansDao;
import com.chen.dao.FollowDao;
import com.chen.splider.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by chen on 2017/6/14.
 */
public class Main {
    public static void main(String[] args) {
        SpliderCore spliderCore = new SpliderCore();
        YunShareSplider shareSplider = new YunShareSplider(spliderCore);
        YunFansSplider yunFansSplider = new YunFansSplider(spliderCore);
        YunFollowSplider yunFollowSplider = new YunFollowSplider(spliderCore);
        FansDao fansDao = new FansDao();
        FollowDao followDao = new FollowDao();


        LinkedBlockingQueue queue = new LinkedBlockingQueue();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i <1 ; i++) {
            executorService.execute(new ThreadSplider(fansDao,followDao,queue));
        }

    }
}
