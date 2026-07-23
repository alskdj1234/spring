package com.kh.spring11.vo.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(name="본인정보 변경 응답 VO")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ChangeAccountResponseVO {
	private boolean status;
	private String message;
}
