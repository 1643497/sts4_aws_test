package com.test.pro04.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.test.pro04.common.SessionInterceptor;
@Configuration
public class WebConfig implements WebMvcConfigurer {
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new SessionInterceptor())
	                .addPathPatterns("/**")  // 모든 경로에 대해 인터셉터 적용
	                .excludePathPatterns("/member/**","/","/css/**","/js/**","/home","/image");  // 로그인/로그아웃 경로는 제외
	    }
}
