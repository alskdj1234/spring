package com.kh.spring11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//우선 Security를 제외하도록 설정
//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Spring11restapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring11restapiApplication.class, args);
	}

}
