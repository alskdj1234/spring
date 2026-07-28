package com.kh.spring11.account;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dao.AccountDao;
import com.kh.spring11.dto.AccountDto;
import com.kh.spring11.service.RandomService;

@SpringBootTest
public class Test06회원생성 {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private RandomService randomService;
	
	@Test
	public void test() {
		Random r = new Random();
		List<String> addrList = List.of(
			"서울 강남구 역삼동",
			"서울 서초구 서초1동",
			"경기 안양시 만안구",
			"경기 수원시 팔달구"
		);
		
		for(int i=1; i <= 1000; i++) {
			accountDao.insert(AccountDto.builder()
				.accountId("dummyuser"+i)
				.accountPassword("DummyUser"+i+"!")
				.accountNickname("더미유저"+i)
				.accountEmail("dummy"+i+"@kh.com")
				.accountPost(randomService.generateNumber(
					r.nextInt(2) + 5//5부터 2개(5~6)
				))
				.accountAddress1(
					addrList.get(r.nextInt(addrList.size()))//랜덤
					//addrList.get(i % addrList.size())//순서대로
				)
				.accountAddress2("OOO아파트 XXX동 AAA호")
			.build());
		}
	}
	
}



