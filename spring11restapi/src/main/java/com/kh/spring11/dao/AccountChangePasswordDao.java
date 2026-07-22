package com.kh.spring11.dao;

import com.kh.spring11.vo.account.AccountChangePasswordVO;

public interface AccountChangePasswordDao {
	AccountChangePasswordVO selectOne(String accountId);
	boolean changePassword (AccountChangePasswordVO accountChangePasswordVO);
}
