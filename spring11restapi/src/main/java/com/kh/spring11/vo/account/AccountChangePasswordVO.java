package com.kh.spring11.vo.account;

import lombok.Data;

@Data
public class AccountChangePasswordVO {
	private String accountPassword;
	private String accountEmail;
	private String accountId;   
}
