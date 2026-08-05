package com.kh.spring11.vo.kakaopay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "무식한 구매 준비요청 결과 데이터")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KakaopayBuyResponseVO {
	private String url;
}
