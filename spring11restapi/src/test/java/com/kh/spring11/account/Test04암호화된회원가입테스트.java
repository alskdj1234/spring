package com.kh.spring11.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dao.AccountDao;
import com.kh.spring11.dto.AccountDto;

@SpringBootTest
public class Test04암호화된회원가입테스트 {
	@Autowired
	private AccountDao accountDao;
	
	@Test
	public void test() {
		AccountDto accountDto = AccountDto.builder()
				.accountId("testuser2")
				.accountEmail("testuser2@kh.com")
				.accountPassword("Testuser2!")
				.accountNickname("테스트유저2")
			.build();
	
		accountDao.insert(accountDto);
	}
}
