package com.chen.splider;

import com.chen.dao.FansDao;
import com.chen.dao.FollowDao;

import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by chen on 2017/6/17.
 */
public class ThreadSplider implements Runnable {
    SpliderCore spliderCore;
    YunShareSplider shareSplider;
    YunFansSplider yunFansSplider;
    YunFollowSplider yunFollowSplider;

    FansDao fansDao;
    FollowDao followDao;

    String s;
    LinkedBlockingQueue<String> ukList = null;

    public ThreadSplider() {
        spliderCore = new SpliderCore();
        shareSplider = new YunShareSplider(spliderCore);
        yunFansSplider = new YunFansSplider(spliderCore);
        yunFollowSplider = new YunFollowSplider(spliderCore);
        fansDao = new FansDao();
        followDao = new FollowDao();
    }

    public ThreadSplider(FansDao fansDao, FollowDao followDao, LinkedBlockingQueue<String> ukList) {
        spliderCore = new SpliderCore();
        shareSplider = new YunShareSplider(spliderCore);
        yunFansSplider = new YunFansSplider(spliderCore);
        yunFollowSplider = new YunFollowSplider(spliderCore);
        this.fansDao = fansDao;
        this.followDao = followDao;
        this.ukList = ukList;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */

    @Override
    public void run() {
        while (true) {
            synchronized (ukList) {
                while (ukList.isEmpty()) {
                    try {
                        ukList = fansDao.getFansUk(ukList);//10个一起取减少数据库访问
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (ukList.isEmpty()) {
                        try {
                            ukList = followDao.getFollowUk(ukList);//10个一起取减少数据库访问
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


            try {
                s = ukList.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean t1 = shareSplider.getShare(s);
            boolean t2 = yunFansSplider.getFans(s);
            boolean t3 = false;
            try {
                fansDao.updateFansClaw(s);
                t3 = yunFollowSplider.getFollow(s);
                followDao.updateFollowClaw(s);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (t1 && t2 && t3) {
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



        }

    }
}
