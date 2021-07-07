package com.dungcuthethao.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dungcuthethao.client.service.impl.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	  @Autowired
	  private CustomUserDetailService userDetailsService;
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
	    http.authorizeRequests().antMatchers("/quan-tri/**").access("hasRole('ROLE_QUAN-TRI')");
	    // Chỉ cho phép user có quyền ADMIN hoặc USER truy cập đường dẫn
	    // /user/**
	    http.authorizeRequests().antMatchers("/thanh-toan/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_NGUOIDUNG')");
	    // Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang
	    // yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
	    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	    
	    // Cấu hình cho Login Form.
	    http.authorizeRequests().and().formLogin()//
	        .loginProcessingUrl("/j_spring_security_login")//
	        .loginPage("/dang-nhap")//
	        .failureUrl("/dang-nhap?message=error")//
	        .usernameParameter("username")//
	        .passwordParameter("password")
	        // Cấu hình cho Logout Page.
	        .and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/");
	  }
	  
	  

}
