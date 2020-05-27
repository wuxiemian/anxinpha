package com.anxinpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 尹硕硕
 * @description
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.anxinpha.user.mapper")
public class AnxinPhaUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnxinPhaUserApplication.class);
    }
}
