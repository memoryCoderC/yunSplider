package com.chen.parser;

import com.chen.entity.FileInfo;
import com.chen.entity.ShareInfo;
import com.chen.exception.CanNotConvertJsonToObjException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by chen on 2017/6/15.
 * 解析分析列表
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
        List<ShareInfo> shareInfoLit = new LinkedList<ShareInfo>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject1 = JSONObject.fromObject(jsonArray.get(i));
            if (jsonObject1.containsKey("album_id")) {//有的页面中会插入专辑

            } else {
                ShareInfo shareInfo = new ShareInfo();
                shareInfo.setShareid(jsonObject1.get("shareid").toString());
                JSONArray filelist = jsonObject1.optJSONArray("filelist");
                List<FileInfo> list = JSONArray.toList(filelist, FileInfo.class);
                shareInfo.setFilelist(list);
                shareInfoLit.add(shareInfo);
            }
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
