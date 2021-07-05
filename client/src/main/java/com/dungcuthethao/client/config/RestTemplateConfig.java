package com.dungcuthethao.client.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate	 restTemplate() {
		RestTemplate restTemplate= new RestTemplate();
		DefaultUriBuilderFactory builderFactory= new DefaultUriBuilderFactory("http://localhost:7070/");
		restTemplate.setUriTemplateHandler(builderFactory);
		return restTemplate;
	}
	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender= new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("kuroshiro054@gmail.com");
		mailSender.setPassword("123zxcvb");
		
		Properties javaMailProperties= new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");
		
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
		
	}

}
