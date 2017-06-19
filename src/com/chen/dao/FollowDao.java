package com.chen.dao;

import com.chen.entity.FollowInfo;
import com.chen.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by chen on 2017/6/16.
 */
public class FollowDao {
    public void saveFollow(FollowInfo followInfo) throws SQLException {
        if (followInfo == null) {
            return;
        }
        String sql = "INSERT INTO `UserInfo` (`uk`, `uname`, `avatar_url`, `album_craw`, `fans_craw`, `follow_craw`, `pubshare_craw`, `album_count`, `fans_count`, `follow_count`, `pubshare_count`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, followInfo.getFollow_uk());
        preparedStatement.setObject(2, followInfo.getFollow_uname());
        preparedStatement.setObject(3, followInfo.getAvatar_url());
        preparedStatement.setObject(4, "0");
        preparedStatement.setObject(5, "0");
        preparedStatement.setObject(6, "0");
        preparedStatement.setObject(7, "0");
        preparedStatement.setObject(8, followInfo.getAlbum_count());
        preparedStatement.setObject(9, followInfo.getFans_count());
        preparedStatement.setObject(10, followInfo.getFollow_count());
        preparedStatement.setObject(11, followInfo.getPubshare_count());


        try {
            int i = preparedStatement.executeUpdate();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }

    public void updateFollowClaw(String follow_uk) throws SQLException {
        String sql = "UPDATE FollowInfo SET is_claw='1' WHERE (`follow_uk`=?) LIMIT 1";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, follow_uk);
        try {
            int i = preparedStatement.executeUpdate();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }

    public LinkedBlockingQueue<String> getFollowUk(LinkedBlockingQueue UKList) throws SQLException {
        String sql = "SELECT follow_uk FROM FollowInfo WHERE is_craw = 0 LIMIT 0, 30";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                try {
                    UKList.put(resultSet.getString(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {

            DBUtil.close(connection, preparedStatement, resultSet);
        }

        return UKList;
    }
}
