package com.chen.dao;

import com.chen.entity.FollowInfo;
import com.chen.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2017/6/16.
 */
public class FollowDao {
    public void saveFollow(FollowInfo followInfo) throws SQLException {
        String sql = "INSERT INTO `FollowInfo` (`follow_uk`, `is_claw`, `album_count`, `avatar_url`, `fans_count`, `follow_count`, `follow_time`, `follow_uname`, `intro`, `is_vip`, `pubshare_count`, `type`, `user_type`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, followInfo.getFollow_uk());
        preparedStatement.setObject(2, 0);
        preparedStatement.setObject(3, followInfo.getAlbum_count());
        preparedStatement.setObject(4, followInfo.getAvatar_url());
        preparedStatement.setObject(5, followInfo.getFans_count());
        preparedStatement.setObject(6, followInfo.getFollow_count());
        preparedStatement.setObject(7, followInfo.getFollow_time());
        preparedStatement.setObject(8, followInfo.getFollow_uname());
        preparedStatement.setObject(9, followInfo.getIntro());
        preparedStatement.setObject(10, followInfo.getIs_vip());
        preparedStatement.setObject(11, followInfo.getPubshare_count());
        preparedStatement.setObject(12, followInfo.getType());
        preparedStatement.setObject(13, followInfo.getUser_type());
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

    public List<String> getFollowUk() throws SQLException {
        List<String> UKList = new ArrayList<String>();
        String sql = "SELECT follow_uk FROM FollowInfo WHERE is_craw = 0 LIMIT 0, 10";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UKList.add(resultSet.getString(1));
            }
        } finally {

            DBUtil.close(connection, preparedStatement, resultSet);
        }

        return UKList;
    }
}
