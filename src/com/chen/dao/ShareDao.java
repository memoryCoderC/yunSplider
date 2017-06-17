package com.chen.dao;

import com.chen.entity.ShareInfo;
import com.chen.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by chen on 2017/6/16.
 */
public class ShareDao {
    public void saveShare(ShareInfo shareInfo) throws SQLException {
        String sql = "INSERT INTO `ShareInfo` (`shareid`, `title`, `uk`, `username`, `shorturl`,`data_id`, `avatar_url`, `category`, `clienttype`, `dCnt`, `dir_cnt`, `feed_time`, `feed_type`, `filecount`, `source_id`, `source_uid`, `tCnt`, `third`, `vCnt`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (shareInfo == null) {
            return;
        }
        preparedStatement.setObject(1, shareInfo.getShareid());
        preparedStatement.setObject(2, shareInfo.getTitle());
        preparedStatement.setObject(3, shareInfo.getUk());
        preparedStatement.setObject(4, shareInfo.getUsername());
        preparedStatement.setObject(5, shareInfo.getShorturl());
        preparedStatement.setObject(6, shareInfo.getData_id());
        preparedStatement.setObject(7, shareInfo.getAvatar_url());
        preparedStatement.setObject(8, shareInfo.getCategory());
        preparedStatement.setObject(9, shareInfo.getClienttype());
        preparedStatement.setObject(10, shareInfo.getdCnt());
        preparedStatement.setObject(11, shareInfo.getDir_cnt());
        preparedStatement.setObject(12, shareInfo.getFeed_time());
        preparedStatement.setObject(13, shareInfo.getFeed_type());
        preparedStatement.setObject(14, shareInfo.getFilecount());
        preparedStatement.setObject(15, shareInfo.getSource_id());
        preparedStatement.setObject(16, shareInfo.getSource_uid());
        preparedStatement.setObject(17, shareInfo.gettCnt());
        preparedStatement.setObject(18, shareInfo.getThird());
        preparedStatement.setObject(19, shareInfo.getvCnt());
        try {
            int i = preparedStatement.executeUpdate();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }
}
