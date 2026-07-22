package com.kh.spring11.dao;

import com.kh.spring11.dto.AccountRefreshDto;

public interface AccountRefreshDao {
	void insertOrUpdate(AccountRefreshDto accountRefreshDto);
	void delete(AccountRefreshDto accountRefreshDto);
	AccountRefreshDto find(AccountRefreshDto accountRefreshDto);
}
