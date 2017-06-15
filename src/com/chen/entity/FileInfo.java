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


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFs_id() {
        return fs_id;
    }

    public void setFs_id(String fs_id) {
        this.fs_id = fs_id;
    }

    public String getIsdir() {
        return isdir;
    }

    public void setIsdir(String isdir) {
        this.isdir = isdir;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServer_filename() {
        return server_filename;
    }

    public void setServer_filename(String server_filename) {
        this.server_filename = server_filename;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
