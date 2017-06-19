package com.chen.dao;

import com.chen.entity.FileInfo;
import com.chen.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by chen on 2017/6/16.
 */
public class FileInfoDao {
    public void saveFile(FileInfo fileInfo) throws SQLException {
        if (fileInfo == null) {
            return;
        }
        String sql = "INSERT INTO `FileInfo` (`md5`, `shareid`, `uk`, `category`, `path`, `server_filename`, `size`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, fileInfo.getMd5());
        preparedStatement.setObject(2, fileInfo.getShareid());
        preparedStatement.setObject(3, fileInfo.getUk());
        preparedStatement.setObject(4, fileInfo.getCategory());
        preparedStatement.setObject(5, fileInfo.getPath());
        preparedStatement.setObject(6, fileInfo.getServer_filename());
        preparedStatement.setObject(7, fileInfo.getSize());

        try {
            int i = preparedStatement.executeUpdate();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }
}
