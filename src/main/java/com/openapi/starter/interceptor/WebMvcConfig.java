package com.openapi.starter.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(new MainPageInterceptor()).addPathPatterns("/main");
		registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/booklist");

	}

}
