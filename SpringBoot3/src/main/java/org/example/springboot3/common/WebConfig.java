package org.example.springboot3.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//配置类，在启动时自动配置，用于拦截器的注册
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册一个拦截器loginInterceptor，且拦截所有请求，除了/user/login
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login","/user/new");

    }


}
