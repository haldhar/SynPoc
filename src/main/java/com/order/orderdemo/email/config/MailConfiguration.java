package com.order.orderdemo.email.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.order.orderdemo.email.EmailService;

@Configuration
public class MailConfiguration {

	@Value("${spring.mail.host}")
	private String emailHost;

	@Value("${spring.mail.port}")
	private int emailPort;

	@Value("${spring.mail.username}")
	private String emailUserName;

	@Value("${spring.mail.password}")
	private String emailPassword;

	@Value("${spring.activemq.host}")
	private String activemqHost;

	@Value("${spring.activemq.port}")
	private int activemqPort;

	@Value("${spring.activemq.username}")
	private String activemqUserName;

	@Value("${spring.activemq.password}")
	private String activemqPassword;

	private static final Logger logger = LoggerFactory.getLogger(MailConfiguration.class);

	@Bean
	public EmailService emailService() {
		return new EmailService(getJavaMailSender());
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		logger.info("Mail properties : [emailHost:" + emailHost + ", port:" + emailPort + ", username:" + emailUserName	+ "]");
		var javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setProtocol("smtp");
		javaMailSender.setHost(emailHost);
		javaMailSender.setPort(emailPort);
		javaMailSender.setUsername(emailUserName);
		javaMailSender.setPassword(emailPassword);
		return javaMailSender;
	}
}
