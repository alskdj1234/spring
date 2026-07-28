package com.kh.spring11.vo.account;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Schema(name = "관리자 회원조회 응답")
@Data @Builder @NotNull @AllArgsConstructor
public class AccountSearchResponseVO {
	private boolean last;
	private List<AccountSearchResultVO> list;
}





