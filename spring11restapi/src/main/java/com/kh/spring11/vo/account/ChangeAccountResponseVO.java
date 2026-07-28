package com.kh.spring11.vo.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Schema(name = "본인정보 변경 응답 VO")
@Data @Builder @NotNull @AllArgsConstructor
public class ChangeAccountResponseVO {
	private boolean status;
	private String message;
}
