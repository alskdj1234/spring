package com.kh.spring11.vo.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema (name = "로그인 처리 응답 데이터")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AuthLoginResponseVO {
	private String accountId;
	private String accountNickname;
	private String accountLevel;
	private boolean needUpdate;
}
