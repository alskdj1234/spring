package com.kh.spring11.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dao.AccountDao;
import com.kh.spring11.dto.AccountDto;

@SpringBootTest
public class Test04암호화된회원가입테스트 {
 @Autowired
 private AccountDao accountDao;
 	public void test() {
 		AccountDto accountDto = AccountDto.builder()
 				.accountId("testuser1")
				.accountEmail("testuser1@kh.com")
				.accountPassword("Testuser1!")
				.accountNickname("테스트유저1")
				.build();
 		accountDao.insert(accountDto);
 	}
}
