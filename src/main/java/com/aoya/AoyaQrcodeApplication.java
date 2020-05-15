package com.aoya;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aoya.mapper")
public class AoyaQrcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AoyaQrcodeApplication.class, args);
    }

}
