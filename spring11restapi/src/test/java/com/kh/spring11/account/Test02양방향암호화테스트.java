package com.kh.spring11.account;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test02양방향암호화테스트 {
	
	//Spring-Security 시스템에서 제공하는 암호화 도구들을 사용해보자
	//1. 프로젝트에 Spring Security를 추가
	//2. 프로젝트 전체가 Spring Security 때문에 잠기는 것을 막도록 설정
	//3. 필요한 암호화 도구들을 가져다 사용

	@Test
	public void test() {
		//양방향 암호화 : 암호화(encryption)와 복호화(decryption)이 가능한 방식
		
		//암호화와 복호화에 사용될 열쇠(key)
		String key = "kh-academy";
		
		//salt : 암호화에 사용될 양념(변조과정을 눈치채지 못하도록 만드는 보조값)
		String salt = "1234567890abcdef";
		
		//양방향 암호화 도구 생성
		TextEncryptor encryptor = Encryptors.delux(key, salt);
		
		//원본 문자열 준비
		String origin = "aaaaaaaaaaaaaaaa";
		
		//암호화
		String encrypt = encryptor.encrypt(origin);
		
		//복호화
		String decrypt = encryptor.decrypt(encrypt);
		
		//출력
		log.debug("origin = {}", origin);
		log.debug("encrypt = {}", encrypt);
		log.debug("encrypt size = {}", encrypt.length());
		log.debug("decrypt = {}", decrypt);
	}
	
}
