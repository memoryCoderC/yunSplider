package com.chen.main;

import com.chen.dao.FansDao;
import com.chen.dao.FollowDao;
import com.chen.splider.SpliderCore;
import com.chen.splider.YunFansSplider;
import com.chen.splider.YunFollowSplider;
import com.chen.splider.YunShareSplider;

import java.sql.SQLException;
import java.util.List;

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


        while (true) {
            List<String> ukList = null;
            try {
                ukList = fansDao.getFansUk();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (ukList == null) {
                try {
                    ukList = followDao.getFollowUk();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (ukList != null) {
                for (String s : ukList) {
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


    }
}
