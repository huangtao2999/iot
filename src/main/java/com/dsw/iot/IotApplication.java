package com.dsw.iot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.dsw.iot.dal")
@EnableScheduling
public class IotApplication {
    public static void main(String[] args) {
        SpringApplication.run(IotApplication.class, args);
    }
}
