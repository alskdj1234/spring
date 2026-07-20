package com.kh.spring11.vo.auth;

import lombok.Data;

@Data
public class AuthLoginRequestVO {
	private String accountId;
	private String accountPassword;
}
