package com.broad.emc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.broad.emc.module.dao")
@SpringBootApplication
public class EmcApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmcApplication.class, args);
    }

}
