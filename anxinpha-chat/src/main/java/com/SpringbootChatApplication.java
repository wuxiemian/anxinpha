package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync //开启异步任务
@EnableScheduling //开启基于注解的定时任务
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan({"com.chat.mapper"})
public class SpringbootChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootChatApplication.class, args);
    }

}
