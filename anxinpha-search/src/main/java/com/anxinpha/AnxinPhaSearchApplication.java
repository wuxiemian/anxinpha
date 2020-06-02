package com.anxinpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 尹硕硕
 * @description
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AnxinPhaSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnxinPhaSearchApplication.class);
    }
}
