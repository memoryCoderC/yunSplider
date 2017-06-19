package com.chen.dao;

import com.chen.entity.AlbumInfo;
import com.chen.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by chen on 2017/6/16.
 */
public class AlbumDao {
    /**
     * @param albumInfo
     * @throws SQLException
     */
    public void saveAlbum(AlbumInfo albumInfo) throws SQLException {
        if (albumInfo == null) {
            return;
        }
        String sql = "INSERT INTO `AlbumInfo` (`album_id`, `uk`, `title`) VALUES (?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, albumInfo.getAlbum_id());
        preparedStatement.setObject(2, albumInfo.getUk());
        preparedStatement.setObject(3, albumInfo.getTitle());

        try {
            int i = preparedStatement.executeUpdate();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }
}
