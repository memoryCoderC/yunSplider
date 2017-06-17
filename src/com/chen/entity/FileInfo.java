package com.chen.entity;

/**
 * Created by chen on 2017/6/15.
 * 百度云存储的文件信息
 */
public class FileInfo {
    private String category;
    private String fs_id;
    private String isdir;
    private String md5;
    private String path;
    private String server_filename;
    private String sign;
    private String size;
    private String time_stamp;
    private String shorturl;

    public String getShorturl() {
        if (shorturl == null) {
            return "";
        }
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
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

    public String getFs_id() {
        if (fs_id == null) {
            return "";
        }
        return fs_id;
    }

    public void setFs_id(String fs_id) {
        this.fs_id = fs_id;
    }

    public String getIsdir() {
        if (isdir == null) {
            return "0";
        }
        return isdir;
    }

    public void setIsdir(String isdir) {
        this.isdir = isdir;
    }

    public String getMd5() {
        if (md5 == null) {
            return "";
        }
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getPath() {
        if (path == null) {
            return "";
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServer_filename() {
        if (server_filename == null) {
            return "";
        }
        return server_filename;
    }

    public void setServer_filename(String server_filename) {
        this.server_filename = server_filename;
    }

    public String getSign() {
        if (sign == null) {
            return "";
        }
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSize() {
        if (size == null) {
            return "";
        }
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime_stamp() {
        if (time_stamp == null) {
            return "";
        }
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
