package com.kh.spring11.account;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kh.spring11.dto.AccountDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test05로그인테스트 {
	//가입할 때 사용했던 PasswordEncoder로 로그인이 가능
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		//로그인에 필요한 데이터를 준비
		String accountId = "testuser1";
		String accountPassword = "Testuser1";
		
		//아이디로 회원 정보를 모두 조회
		AccountDto findAccountDto = sqlSession.selectOne(
			"mapper.account.find",
			AccountDto.builder()
				.accountId(accountId)
				.accountPassword(accountPassword)
			.build()
		);
		if(findAccountDto == null) {
			log.error("아이디가 존재하지 않습니다");
			return;
		}
		
		//비밀번호 비교
		//- bcrypt는 암호화 할 때마다 값이 달라지므로 equals 사용이 불가능
		//- PasswordEncoder에서 제공하는 비교 명령으로만 비교 가능
		//boolean match = accountPassword.equals(findAccountDto.getAccountPassword());
		boolean match = passwordEncoder.matches(
				accountPassword, //날것(현재 사용자가 로그인하려고 입력한 값)
				findAccountDto.getAccountPassword() //변환된값(기존 저장된 비밀번호)
		);
		if(match == false) {
			log.error("비밀번호가 일치하지 않습니다");
			return;
		}
		
		//최종 로그인 판정
		log.debug("로그인 성공!");
		
	}
}





