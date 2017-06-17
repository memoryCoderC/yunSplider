package com.chen.entity;

/**
 * Created by chen on 2017/6/15.
 * 文件分析信息
 */
public class ShareInfo {
    private String avatar_url;
    private String category;
    private String clienttype;
    private String dCnt;
    private String data_id;
    private String desc;
    private String dir_cnt;
    private String feed_time;
    private String feed_type;
    private String filecount;
    private String shareid;
    private String shorturl;
    private String source_id;
    private String source_uid;
    private String tCnt;
    private String third;
    private String title;
    private String uk;
    private String username;
    private String vCnt;
    private FileInfo[] filelist;

    public FileInfo[] getFilelist() {
        return filelist;
    }

    public void setFilelist(FileInfo[] filelist) {
        this.filelist = filelist;
    }

    public String getAvatar_url() {
        if (avatar_url == null) {
            return "";
        }
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getCategory() {
        if (category == null) {
            return "";
        }
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClienttype() {
        if (clienttype == null) {
            return "0";
        }
        return clienttype;
    }

    public void setClienttype(String clienttype) {
        this.clienttype = clienttype;
    }

    public String getdCnt() {
        if (dCnt == null) {
            return "";
        }
        return dCnt;
    }

    public void setdCnt(String dCnt) {
        this.dCnt = dCnt;
    }

    public String getData_id() {
        if (data_id == null) {
            return "";
        }
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

    public String getDesc() {
        if (desc == null) {
            return "";
        }
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDir_cnt() {
        if (dir_cnt == null) {
            return "0";
        }
        return dir_cnt;
    }

    public void setDir_cnt(String dir_cnt) {
        this.dir_cnt = dir_cnt;
    }

    public String getFeed_time() {
        if (feed_time == null) {
            return "";
        }
        return feed_time;
    }

    public void setFeed_time(String feed_time) {
        this.feed_time = feed_time;
    }

    public String getFeed_type() {
        if (feed_type == null) {
            return "";
        }
        return feed_type;
    }

    public void setFeed_type(String feed_type) {
        this.feed_type = feed_type;
    }

    public String getFilecount() {
        if (filecount == null) {
            return "";
        }
        return filecount;
    }

    public void setFilecount(String filecount) {
        this.filecount = filecount;
    }

    public String getShareid() {
        if (shareid == null) {
            return "";
        }
        return shareid;
    }

    public void setShareid(String shareid) {
        this.shareid = shareid;
    }

    public String getShorturl() {
        if (shorturl == null) {
            return "";
        }
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public String getSource_id() {
        if (source_id == null) {
            return "";
        }
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getSource_uid() {
        if (source_uid == null) {
            return "";
        }
        return source_uid;
    }

    public void setSource_uid(String source_uid) {
        this.source_uid = source_uid;
    }

    public String gettCnt() {
        if (tCnt == null) {
            return "";
        }
        return tCnt;
    }

    public void settCnt(String tCnt) {
        this.tCnt = tCnt;
    }

    public String getThird() {
        if (third == null) {
            return "";
        }
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getTitle() {
        if (title == null) {
            return "";
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUk() {
        if (uk == null) {
            return "";
        }
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }

    public String getUsername() {
        if (username == null) {
            return "";
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getvCnt() {
        if (vCnt == null) {
            return "";
        }
        return vCnt;
    }

    public void setvCnt(String vCnt) {
        this.vCnt = vCnt;
    }
}
