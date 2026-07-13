package com.kh.spring11.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//json 처리방침 변경 하는 설정
//스프링에서는 잭슨 데어터 바인드라는 기술에서 제공하는 오브젝트매퍼를 기본 변환도구로 사용
//오브젝트매퍼를 대체할 신규 오브젝트 매퍼를 만들어서 교체설정
@Configuration
public class JsonConfiguration {
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		
		//자바 8+ 시간 방식 해석이 가능하도록 설정
		mapper.registerModule(new JavaTimeModule());
		//문자열 해석 기준 재설정 (공백은 널로 간주)
		SimpleModule module = new SimpleModule();
		module.addDeserializer(String.class, new EmptyStringToNullDeserializer());
		mapper.registerModule(module);
		
		
		return mapper;
	}

}
