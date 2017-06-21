package com.chen.parser;


import com.chen.entity.FollowInfo;
import com.chen.exception.CanNotConvertJsonToObjException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by chen on 2017/6/14.
 * 解析用户列表
 */
public class FollowParser {
    public static final String followListKey = "follow_list";//粉丝列表的键
    public static final String totalCountKey = "total_count";//总数的键


    public List<FollowInfo> parseFollowInfo(String json) throws CanNotConvertJsonToObjException {
        JSONObject jsonObject = JSONObject.fromObject(json);
        return parseFollowInfo(jsonObject);
    }

    public List<FollowInfo> parseFollowInfo(JSONObject jsonObject) throws CanNotConvertJsonToObjException {
        Object followList = jsonObject.get(followListKey);
        //3、转成数组
        JSONArray jsonArray = JSONArray.fromObject(followList);
        //4、把把数组转成列表
        List<FollowInfo> followInfoList = null;
        try {
            followInfoList = JSONArray.toList(jsonArray, FollowInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CanNotConvertJsonToObjException(FollowInfo.class.getName());
        }

        return followInfoList;
    }

    public int getTotalCount(String json) throws CanNotConvertJsonToObjException {
        int count = 0;
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            Object totalCount = jsonObject.get(totalCountKey);
            count = (int) totalCount;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            throw new CanNotConvertJsonToObjException(Integer.class.getName());
        }
        return count;

    }
}
