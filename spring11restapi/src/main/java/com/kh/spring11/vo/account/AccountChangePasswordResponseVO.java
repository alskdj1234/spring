package com.kh.spring11.vo.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(name = "비밀번호 바꾸기 정보 반환")
@Data
public class AccountChangePasswordResponseVO {
	private String accountPassword;
}
