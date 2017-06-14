package com.chen.parser;

import com.chen.entity.UserInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by chen on 2017/6/14.
 */
public class fansParser{
    public static final String fansListKey = "fans_list";



    public List<UserInfo> parse(String result) {
        JSONObject jsonObject = JSONObject.fromObject(result);
        Object fansList = jsonObject.get(fansListKey);
        //3、转成数组
        JSONArray jsonArray = JSONArray.fromObject(fansList);
        //4、把把数组转成列表
        List<UserInfo> userInfoLit = JSONArray.toList(jsonArray, UserInfo.class);
        return userInfoLit;
    }
}
