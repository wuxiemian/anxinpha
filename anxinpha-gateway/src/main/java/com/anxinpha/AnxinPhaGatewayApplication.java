package com.anxinpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 尹硕硕
 * @description 网关启动器
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class AnxinPhaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnxinPhaGatewayApplication.class);
    }
}

