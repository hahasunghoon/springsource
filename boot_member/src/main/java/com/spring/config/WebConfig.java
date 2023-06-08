package com.spring.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.interceptor.AuthCheckinterceptor;

public class WebConfig implements WebMvcConfigurer {
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthCheckinterceptor())
		        .addPathPatterns("/member/changePwd");
	}

}
