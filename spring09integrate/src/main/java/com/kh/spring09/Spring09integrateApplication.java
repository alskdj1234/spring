package com.kh.spring09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync//이제부터 이 프로그램에서는 비동기 시스템을 사용할거에요~
public class Spring09integrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring09integrateApplication.class, args);
	}

}
