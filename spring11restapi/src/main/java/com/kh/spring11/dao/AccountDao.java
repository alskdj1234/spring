package com.kh.spring11.dao;

import com.kh.spring11.dto.AccountDto;

public interface AccountDao {
	void insert(AccountDto accountDto);
	
	AccountDto selectOne(String accountId);
	
	boolean checkAvailableId(String accountId);
	boolean checkAvailableNickname(String accountNickname);
	boolean checkAvailableEmail(String accountEmail);
	boolean updateAccountLogin(String accountId);
	boolean updateAccountPassword(AccountDto accountDto);
}
