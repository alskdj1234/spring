package com.kh.spring11.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.service.KoreanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test08한글가지고놀기2 {
	
	@Autowired
	private KoreanService koreanService;
	
	@Test
	public void test() {
		String origin = "황인빈";
		String text = "황";
		
		String originDisassemble = koreanService.disassemble(origin);
		String textDisassemble = koreanService.disassemble(text);
		log.debug("원본 = {}", originDisassemble);
		log.debug("검색 = {}", textDisassemble);
		
		boolean match = originDisassemble.startsWith(textDisassemble);
		log.debug("결과 = {}", match);
	}
	
}
