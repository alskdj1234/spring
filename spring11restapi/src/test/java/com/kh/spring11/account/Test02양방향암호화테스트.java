package com.kh.spring11.account;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@SpringBootTest
public class Test02양방향암호화테스트 {
//스프링 시큐리티 시스템에서 제공하는 암호화 도구 사용하기
//1. 프로젝트에 스프링 시큐리티를 추가
//2.전체 잠기는 걸 막아야하고 필요한 도구를 가져다 사용	
	@Test
	public void test() {
		//양방향 암호화 :암호화(encryption) 복호화(decryption)이 가능
		String key="kh-academy";
		
		//salt : 암호화에 사용될 양념(변조 과정을 눈치 못채게, 원본을 모르게 만드는 보조값)
		String salt ="1234567890abcdef";
		
		//양방향 암호화 도구 생성
		TextEncryptor encryptor = Encryptors.delux(key, salt);
		
		//원본
		String origin = "https://www.naver.com";
		
		String encrypt= encryptor.encrypt(origin);
		
		String decrypt = encryptor.decrypt(encrypt);
	}
}
