package com.chen.entity;

/**
 * Created by chen on 2017/6/15.
 * 百度云存储的文件信息
 */
public class FileInfo {
    private String md5;
    private String uk;
    private String shareid;
    private String category;
    private String isdir;
    private String path;
    private String server_filename;
    private String size;

    public String getMd5() {
        if (md5 == null) {
            return "";
        }
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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

    public String getShareid() {
        if (shareid == null) {
            return "";
        }
        return shareid;
    }

    public void setShareid(String shareid) {
        this.shareid = shareid;
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

    public String getIsdir() {
        if (isdir == null) {
            return "";
        }
        return isdir;
    }

    public void setIsdir(String isdir) {
        this.isdir = isdir;
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

    public String getSize() {
        if (size == null) {
            return "";
        }
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
