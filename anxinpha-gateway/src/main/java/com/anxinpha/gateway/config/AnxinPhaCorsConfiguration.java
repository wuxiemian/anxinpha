package com.anxinpha.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author 尹硕硕
 * @description cors解决跨域问题配置类
 **/
@Configuration
public class AnxinPhaCorsConfiguration {

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration configuration = new CorsConfiguration();
        //添加跨域白名单
        configuration.addAllowedOrigin("http://manage.anxinpha.com");
        configuration.addAllowedOrigin("http://www.anxinpha.com");
        configuration.addAllowedOrigin("http://api.anxinpha.com");
        configuration.addAllowedOrigin("http://chat.anxinpha.com");
        //允许携带Cookie
        configuration.setAllowCredentials(true);
        //允许所有的方法
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",configuration);
        return new CorsFilter(configurationSource);
    }
}
