package com.kh.spring11.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data @Component @ConfigurationProperties(prefix = "custom.login")
public class LoginProperties {
	private int needUpdateTerm;
}
