package com.example.filter_interceptor_aop.config;

import com.example.filter_interceptor_aop.interceptor.OpenApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private OpenApiInterceptor openApiInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(openApiInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns();
    }
}
