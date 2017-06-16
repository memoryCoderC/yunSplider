package com.chen.dao;

import com.chen.entity.FileInfo;
import com.chen.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by chen on 2017/6/16.
 */
public class FileDao {
    public void saveFile(FileInfo fileInfo) throws SQLException {
        String sql = "INSERT INTO `FileInfo` (`category`, `fs_id`, `isdir`, `md5`, `path`, `server_filename`, `sign`, `size`, `time_stamp`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, fileInfo.getCategory());
        preparedStatement.setObject(2, fileInfo.getFs_id());
        preparedStatement.setObject(3, fileInfo.getIsdir());
        preparedStatement.setObject(4, fileInfo.getMd5());
        preparedStatement.setObject(5, fileInfo.getPath());
        preparedStatement.setObject(6, fileInfo.getServer_filename());
        preparedStatement.setObject(7, fileInfo.getSign());
        preparedStatement.setObject(8, fileInfo.getSize());
        preparedStatement.setObject(9, fileInfo.getTime_stamp());
        try {
            int i = preparedStatement.executeUpdate();
        }finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }
}
