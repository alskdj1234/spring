package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.AccountDto;
import com.kh.spring11.vo.account.AccountSearchRequestVO;
import com.kh.spring11.vo.account.AccountSearchResultVO;

public interface AccountDao {
	void insert(AccountDto accountDto);
	
	AccountDto selectOne(String accountId);
	
	boolean checkAvailableId(String accountId);
	boolean checkAvailableNickname(String accountNickname);
	boolean checkAvailableEmail(String accountEmail);
	
	boolean updateAccountLogin(String accountId);
	boolean updateAccountPassword(AccountDto accountDto);
	boolean updateAll(AccountDto accountDto);
	
	List<AccountSearchResultVO> search(AccountSearchRequestVO request);
	int count(AccountSearchRequestVO request);

	boolean updateAccountBlock(AccountDto accountDto);

	boolean updateAccountChange(String accountId);
}








