package com.aoya.domain;

import java.util.Date;

public class AyCode {
    private Integer aycId;

    private String aycName;

    private String aycImgUrl;

    private String aycCodeUrl;

    private Date aycUpdateTime;

    public Integer getAycId() {
        return aycId;
    }

    public void setAycId(Integer aycId) {
        this.aycId = aycId;
    }

    public String getAycName() {
        return aycName;
    }

    public void setAycName(String aycName) {
        this.aycName = aycName == null ? null : aycName.trim();
    }

    public String getAycImgUrl() {
        return aycImgUrl;
    }

    public void setAycImgUrl(String aycImgUrl) {
        this.aycImgUrl = aycImgUrl == null ? null : aycImgUrl.trim();
    }

    public String getAycCodeUrl() {
        return aycCodeUrl;
    }

    public void setAycCodeUrl(String aycCodeUrl) {
        this.aycCodeUrl = aycCodeUrl == null ? null : aycCodeUrl.trim();
    }

    public Date getAycUpdateTime() {
        return aycUpdateTime;
    }

    public void setAycUpdateTime(Date aycUpdateTime) {
        this.aycUpdateTime = aycUpdateTime;
    }
}