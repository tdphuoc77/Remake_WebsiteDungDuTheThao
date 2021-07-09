package com.dungcuthethao.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dungcuthethao.client.intercepter.MenuHandleIntercepter;

@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private MenuHandleIntercepter menu;
		
	@Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        // LogInterceptor áp dụng cho mọi URL.
	        registry.addInterceptor(menu)
	        .addPathPatterns("/*")
	        .excludePathPatterns("/quan-tri/**");
	 
	        // Đường dẫn login cũ, không còn sử dụng nữa.
	        // Sử dụng OldURLInterceptor để điều hướng tới một URL mới.
//	        registry.addInterceptor(new OldLoginInterceptor())//
//	                .addPathPatterns("/admin/oldLogin");
	 
	        // Interceptor này áp dụng cho các URL có dạng /admin/*
	        // Loại đi trường hợp /admin/oldLogin
//	        registry.addInterceptor(new AdminInterceptor())//
//	                .addPathPatterns("/admin/*")//
//	                .excludePathPatterns("/admin/oldLogin");
	    }
	 
}
