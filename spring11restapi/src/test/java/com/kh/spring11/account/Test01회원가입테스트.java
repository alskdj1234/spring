package com.kh.spring11.account;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dto.AccountDto;

@SpringBootTest
public class Test01회원가입테스트 {
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void insert() {
		AccountDto accountDto = AccountDto.builder()
					.accountId("testuser1")
					.accountEmail("testuser1@kh.com")
					.accountPassword("Testuser1!")
					.accountNickname("테스트유저1")
					.build();
		sqlSession.insert("mapper.account.join",accountDto);
	}
}
