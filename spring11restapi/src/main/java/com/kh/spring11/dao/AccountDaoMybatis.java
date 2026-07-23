package com.kh.spring11.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.AccountDto;

@Repository
public class AccountDaoMybatis implements AccountDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 

	@Override
	public void insert(AccountDto accountDto) {
		//사용자가 입력한 암호를 BCrypt 방식으로 암호화하여 재설정 후 등록
		String origin = accountDto.getAccountPassword();
		String encrypt = passwordEncoder.encode(origin);
		accountDto.setAccountPassword(encrypt);
		sqlSession.insert("mapper.account.join", accountDto);
	}

	@Override
	public AccountDto selectOne(String accountId) {
		return sqlSession.selectOne("mapper.account.find", accountId);
	}

	@Override
	public boolean checkAvailableId(String accountId) {
		int count = sqlSession.selectOne("mapper.account.countAccountId", accountId);
		return count == 0;
	}
	@Override
	public boolean checkAvailableNickname(String accountNickname) {
		int count = sqlSession.selectOne("mapper.account.countAccountNickname", accountNickname);
		return count == 0;
	}
	@Override
	public boolean checkAvailableEmail(String accountEmail) {
		int count = sqlSession.selectOne("mapper.account.countAccountEmail", accountEmail);
		return count == 0;
	}
	
	@Override
	public boolean updateAccountLogin(String accountId) {
		return sqlSession.update("mapper.account.updateAccountLogin", accountId) > 0;
	}

	@Override
	public boolean updateAccountPassword(AccountDto accountDto) {
		
		String origin = accountDto.getAccountPassword();
		String encrypt = passwordEncoder.encode(origin);
		accountDto.setAccountPassword(encrypt);
		return sqlSession.update("mapper.account.updateAccountPassword", accountDto) > 0;
	}

	@Override
	public boolean updateAll(AccountDto accountDto) {
		return sqlSession.update("mapper.account.updateAll", accountDto)>0;
	}
	

}


