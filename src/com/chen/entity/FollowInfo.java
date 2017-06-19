package com.chen.entity;

/**
 * Created by chen on 2017/6/15.
 */
public class FollowInfo {
    private String album_count;
    private String avatar_url;
    private String fans_count;
    private String follow_count;
    private String follow_uk;
    private String follow_uname;
    private String pubshare_count;

    public String getAlbum_count() {
        if (album_count == null) {
            return "";
        }
        return album_count;
    }

    public void setAlbum_count(String album_count) {
        this.album_count = album_count;
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

    public String getFans_count() {
        if (fans_count == null) {
            return "";
        }
        return fans_count;
    }

    public void setFans_count(String fans_count) {
        this.fans_count = fans_count;
    }

    public String getFollow_count() {
        if (follow_count == null) {
            return "";
        }
        return follow_count;
    }

    public void setFollow_count(String follow_count) {
        this.follow_count = follow_count;
    }


    public String getFollow_uk() {
        if (follow_uk == null) {
            return "";
        }
        return follow_uk;
    }

    public void setFollow_uk(String follow_uk) {
        this.follow_uk = follow_uk;
    }

    public String getFollow_uname() {
        if (follow_uname == null) {
            return "";
        }
        return follow_uname;
    }

    public void setFollow_uname(String follow_uname) {
        this.follow_uname = follow_uname;
    }


    public String getPubshare_count() {
        if (pubshare_count == null) {
            return "";
        }
        return pubshare_count;
    }

    public void setPubshare_count(String pubshare_count) {
        this.pubshare_count = pubshare_count;
    }


}
