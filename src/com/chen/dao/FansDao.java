package com.chen.dao;

import com.chen.entity.FansInfo;
import com.chen.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by chen on 2017/6/16.
 */
public class FansDao {
    /**
     * @param fansInfo
     * @throws SQLException
     */
    public void saveFans(FansInfo fansInfo) throws SQLException {
        String sql = "INSERT INTO `FansInfo` (`fans_uk`, `is_craw`, `album_count`, `avatar_url`, `fans_count`, `fans_uname`, `follow_count`, `follow_time`, `intro`, `is_vip`, `pubshare_count`, `type`, `Suser_type`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (fansInfo == null) {
            return;
        }
        preparedStatement.setObject(1, fansInfo.getFans_uk());
        preparedStatement.setObject(2, 0);
        preparedStatement.setObject(3, fansInfo.getAlbum_count());
        preparedStatement.setObject(4, fansInfo.getAvatar_url());
        preparedStatement.setObject(5, fansInfo.getFans_count());
        preparedStatement.setObject(6, fansInfo.getFans_uname());
        preparedStatement.setObject(7, fansInfo.getFollow_count());
        preparedStatement.setObject(8, fansInfo.getFollow_time());
        preparedStatement.setObject(9, fansInfo.getIntro());
        preparedStatement.setObject(10, fansInfo.getIs_vip());
        preparedStatement.setObject(11, fansInfo.getPubshare_count());
        preparedStatement.setObject(12, fansInfo.getType());
        preparedStatement.setObject(13, fansInfo.getSuser_type());
        try {
            int i = preparedStatement.executeUpdate();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }

    public void updateFansClaw(String fans_uk) throws SQLException {
        String sql = "UPDATE FansInfo SET is_craw='1' WHERE (`fans_uk`=?) LIMIT 1";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, fans_uk);
        try {
            int i = preparedStatement.executeUpdate();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }

    public LinkedBlockingQueue<String> getFansUk(LinkedBlockingQueue UKList) throws SQLException {
        String sql = "SELECT fans_uk FROM FansInfo WHERE is_craw = 0 LIMIT 0, 30";
        Connection connection = DBUtil.getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                try {
                    UKList.put(resultSet.getString(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
        return UKList;
    }
}
