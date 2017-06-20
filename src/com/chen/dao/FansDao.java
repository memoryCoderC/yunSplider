package com.chen.dao;

import com.chen.entity.FansInfo;
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
public class FansDao {
    public static final String COLUMN_ALBUM_CRAW = "album_craw";
    public static final String COLUMN_FANS_CRAW = "fans_craw";
    public static final String COLUMN_FOLLOW_CRAW = "follow_craw";
    public static final String COLUMN_PUBSHARE_CRAW = "pubshare_craw";

    /**
     * @param fansInfo
     * @throws SQLException
     */
    public void saveFans(FansInfo fansInfo) throws SQLException {
        if (fansInfo == null) {
            return;
        }
        String sql = "INSERT INTO `UserInfo` (`uk`, `uname`, `avatar_url`, `album_craw`, `fans_craw`, `follow_craw`, `pubshare_craw`, `album_count`, `fans_count`, `follow_count`, `pubshare_count`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, fansInfo.getFans_uk());
        preparedStatement.setObject(2, fansInfo.getFans_uname());
        preparedStatement.setObject(3, fansInfo.getAvatar_url());
        preparedStatement.setObject(4, "0");
        preparedStatement.setObject(5, "0");
        preparedStatement.setObject(6, "0");
        preparedStatement.setObject(7, "0");
        preparedStatement.setObject(8, fansInfo.getAlbum_count());
        preparedStatement.setObject(9, fansInfo.getFans_count());
        preparedStatement.setObject(10, fansInfo.getFollow_count());
        preparedStatement.setObject(11, fansInfo.getPubshare_count());

        try {
            int i = preparedStatement.executeUpdate();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }

    public void updateClaw(String column, String uk) throws SQLException {
        String sql = "UPDATE UserInfo SET " + column + "=1 WHERE (`uk`=?) LIMIT 1";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, uk);
        try {
            int i = preparedStatement.executeUpdate();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }

    public List<String> getUkList(String column) throws SQLException {
        String sql = "SELECT uk FROM UserInfo WHERE " + column + " = 0 LIMIT 0, 30";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        List<String> ukList = new ArrayList<String>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ukList.add(resultSet.getString(1));
            }
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
        return ukList;
    }
}
