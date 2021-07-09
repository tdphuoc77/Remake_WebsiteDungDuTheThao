package com.dungcuthethao.client.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dungcuthethao.client.security.CustomSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Resource(name = "customUserService")
	 private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Sét đặt dịch vụ để tìm kiếm User trong Database.
		// Và sét đặt PasswordEncoder.
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Chỉ cho phép user có quyền ADMIN truy cập đường dẫn /admin/**
		http.authorizeRequests().antMatchers("/quan-tri/**").access("hasRole('ROLE_QUANTRI')");
		// Chỉ cho phép user có quyền ADMIN hoặc USER truy cập đường dẫn
		// /user/**
		http.authorizeRequests().antMatchers("/thanh-toan/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_NGUOIDUNG')");
		// Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang
		// yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Cấu hình cho Login Form.
		http.authorizeRequests()
				.and()
				.formLogin()//
				.loginPage("/dang-nhap")//
				.permitAll()
				.usernameParameter("username")//
				.passwordParameter("password")
				.loginProcessingUrl("/dang-nhap")//
				.successHandler(successHandler())
				.failureUrl("/dang-nhap?incorrectAccount")//
				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/");
		
		http.csrf().disable();
	}

	@Bean
	public CustomSuccessHandler successHandler() {
		return new CustomSuccessHandler();
	}
}
