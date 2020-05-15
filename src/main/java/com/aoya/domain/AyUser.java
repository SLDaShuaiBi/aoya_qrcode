package com.aoya.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AyUser {
    private Integer ayuId;

    private String ayuName;

    private String ayuPhone;

    private Date ayuUpdateTime;
}