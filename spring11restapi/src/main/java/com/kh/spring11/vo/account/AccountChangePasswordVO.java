package com.kh.spring11.vo.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(name = "비밀번호 바꾸기 정보 요청")
@Data
public class AccountChangePasswordVO {
	private String accountPassword;
	private String accountEmail;
	private String accountId;
	private String certNumber;
}
