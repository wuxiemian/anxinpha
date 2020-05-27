package com.anxinpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 尹硕硕
 * @description 文件上传启动类
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class AnxinPhaUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnxinPhaUploadApplication.class);
    }
}
