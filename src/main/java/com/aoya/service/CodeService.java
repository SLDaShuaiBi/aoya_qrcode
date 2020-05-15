package com.aoya.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program:aoya_qrcode
 * @Author:SL
 * @Date:2020-5-12 11:19
 */
@Service
public class CodeService {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
    }
}
