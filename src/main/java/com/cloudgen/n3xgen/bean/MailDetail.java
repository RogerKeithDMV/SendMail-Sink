package com.cloudgen.n3xgen.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "mail.send")
@Validated
public class MailDetail {
	private String recipient;
	private String subject;
	//private String msgBody="";
	private String host;
	private String username;
	private String password;
}