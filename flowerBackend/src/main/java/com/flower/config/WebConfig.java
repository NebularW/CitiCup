package com.flower.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @title: WebConfig
 * @Author: Stanton JY
 * @Date: 2022/2/24 23:16
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //过滤所有请求
                .addMapping("/**")
                //配置跨域来源
                .allowedOrigins("http://localhost:9001", "null")
                //是否支持跨域Cookie
                .allowCredentials(true)
                //最大响应时间
                .maxAge(3600)
                //设置允许访问的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
