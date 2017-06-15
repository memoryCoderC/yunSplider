package com.chen.parser;

import com.chen.entity.ShareInfo;
import com.chen.exception.CanNotConvertJsonToObjException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by chen on 2017/6/15.
 */
public class SharePaser {
    public static final String recordsKey = "records";//粉丝列表的键
    public static final String totalCountKey = "total_count";//总数的键


    public List<ShareInfo> parseShareInfo(String json) throws CanNotConvertJsonToObjException {
        JSONObject jsonObject = JSONObject.fromObject(json);
        return parseShareInfo(jsonObject);
    }

    public List<ShareInfo> parseShareInfo(JSONObject jsonObject) throws CanNotConvertJsonToObjException {
        Object shareList = jsonObject.get(recordsKey);
        //3、转成数组
        JSONArray jsonArray = JSONArray.fromObject(shareList);
        //4、把把数组转成列表
        List<ShareInfo> shareInfoLit = null;
        try {
            shareInfoLit = JSONArray.toList(jsonArray, ShareInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CanNotConvertJsonToObjException(ShareInfo.class.getName());
        }
        return shareInfoLit;
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
