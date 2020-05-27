package com.anxinpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 尹硕硕
 * @description 注册中心启动器
 **/
@SpringBootApplication
@EnableEurekaServer
public class AnxinPhaRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnxinPhaRegistryApplication.class);
    }
}
