package com.chen.parser;

import com.chen.entity.FansInfo;
import com.chen.exception.CanNotConvertJsonToObjException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by chen on 2017/6/14.
 * 解析用户列表
 */
public class FansParser {
    public static final String fansListKey = "fans_list";//粉丝列表的键
    public static final String totalCountKey = "total_count";//总数的键


    public List<FansInfo> parseFansInfo(String json) throws CanNotConvertJsonToObjException {
        JSONObject jsonObject = JSONObject.fromObject(json);
        return parseFansInfo(jsonObject);
    }

    public List<FansInfo> parseFansInfo(JSONObject jsonObject) throws CanNotConvertJsonToObjException {
        Object fansList = jsonObject.get(fansListKey);
        //3、转成数组
        JSONArray jsonArray = JSONArray.fromObject(fansList);
        //4、把把数组转成列表
        List<FansInfo> fansInfoLit = null;
        try {
            fansInfoLit = JSONArray.toList(jsonArray, FansInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CanNotConvertJsonToObjException(FansInfo.class.getName());
        }
        return fansInfoLit;
    }

    public int getTotalCount(String json) throws CanNotConvertJsonToObjException {
        int count = 0;
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            Object totalCount = jsonObject.get(totalCountKey);
            count = (int) totalCount;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CanNotConvertJsonToObjException(Integer.class.getName());
        }
        return count;

    }
}
