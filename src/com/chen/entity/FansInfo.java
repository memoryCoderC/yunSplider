package com.chen.entity;

/**
 * Created by chen on 2017/6/14.
 *y
 */
public class FansInfo {
    private String album_count;
    private String avatar_url;
    private String fans_count;
    private String fans_uk;
    private String fans_uname;
    private String follow_count;
    private String follow_time;
    private String intro;
    private String is_vip;
    private String pubshare_count;
    private String type;
    private String Suser_type;

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

    public String getFans_uk() {
        if (fans_uk == null) {
            return "";
        }
        return fans_uk;
    }

    public void setFans_uk(String fans_uk) {
        this.fans_uk = fans_uk;
    }

    public String getFans_uname() {
        if (fans_uname == null) {
            return "";
        }
        return fans_uname;
    }

    public void setFans_uname(String fans_uname) {
        this.fans_uname = fans_uname;
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

    public String getFollow_time() {
        if (follow_time == null) {
            return "";
        }
        return follow_time;
    }

    public void setFollow_time(String follow_time) {
        this.follow_time = follow_time;
    }

    public String getIntro() {
        if (intro == null) {
            return "";
        }
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIs_vip() {
        if (is_vip == null) {
            return "0";
        }
        return is_vip;
    }

    public void setIs_vip(String is_vip) {
        this.is_vip = is_vip;
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

    public String getType() {
        if (type == null) {
            return "";
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuser_type() {
        if (Suser_type == null) {
            return "";
        }
        return Suser_type;
    }

    public void setSuser_type(String suser_type) {
        Suser_type = suser_type;
    }
}
