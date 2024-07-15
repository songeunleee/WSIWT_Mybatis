package com.example.wsiwt_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan
@SpringBootApplication

public class WsiwtBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsiwtBackApplication.class, args);
    }

}
