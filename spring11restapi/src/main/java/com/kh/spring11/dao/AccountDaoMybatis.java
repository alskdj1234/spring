package com.kh.spring11.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.AccountDto;

@Repository
public class AccountDaoMybatis implements AccountDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void insert(AccountDto accountDto) {
		//사용자 입력 값을 비크립트 방식으로 암호화하여 재설정
		String origin=accountDto.getAccountPassword();
		String encrypt = passwordEncoder.encode(origin);
		accountDto.setAccountPassword(encrypt);
		sqlSession.insert("maaper.account.join",accountDto);
		
	}

	@Override
	public AccountDto selectOne(String accountId) {
		return sqlSession.selectOne("mapper.account.find", accountId);
	}

	@Override
	public boolean checkAvailableId(String accountId) {
		
		int count = sqlSession.selectOne("mapper.account.countAccountId", accountId);
		return count == 0 ;
	}

	@Override
	public boolean checkAvailableEmail(String accountEmail) {
		int count = sqlSession.selectOne("mapper.account.countAccountEmail", accountEmail);
		return count == 0 ;
	}

	@Override
	public boolean checkAvailableNickname(String accountNickname) {
		int count = sqlSession.selectOne("mapper.account.countAccountNickname", accountNickname);
		return count == 0 ;
	}
	
	
}
