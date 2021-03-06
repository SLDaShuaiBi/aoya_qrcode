package com.aoya.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AyUser {
    private Integer ayuId;

    private String ayuName;

    private String ayuPhone;

    private Date ayuUpdateTime;

    public AyUser() {}

    public AyUser(String ayuName, String ayuPhone) {
        this.ayuName = ayuName;
        this.ayuPhone = ayuPhone;
    }
}