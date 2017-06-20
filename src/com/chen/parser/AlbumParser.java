package com.chen.parser;

import com.chen.entity.AlbumInfo;
import com.chen.exception.CanNotConvertJsonToObjException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by chen on 2017/6/14.
 * 解析用户列表
 */
public class AlbumParser {
    public static final String albumListKey = "album_list";//粉丝列表的键
    public static final String totalCountKey = "count";//总数的键


    public List<AlbumInfo> parseAlbumInfo(String json) throws CanNotConvertJsonToObjException {
        JSONObject jsonObject = JSONObject.fromObject(json);
        return parseFansInfo(jsonObject);
    }

    public List<AlbumInfo> parseFansInfo(JSONObject jsonObject) throws CanNotConvertJsonToObjException {
        Object albumList = jsonObject.get(albumListKey);
        //3、转成数组
        JSONArray jsonArray = JSONArray.fromObject(albumList);
        //4、把把数组转成列表
        List<AlbumInfo> fansInfoLit = null;
        try {
            fansInfoLit = JSONArray.toList(jsonArray, AlbumInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CanNotConvertJsonToObjException(AlbumParser.class.getName());
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
