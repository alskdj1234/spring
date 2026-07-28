package com.kh.spring11.vo.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(name ="계정 차단 결과 응답")

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AccountBlockResponseVO {
	private boolean result;//true 차단 false 차단해제
	
}
