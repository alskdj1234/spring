package com.kh.spring11.vo.purchase;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "현재 사용자의 장바구니 조회 데이터")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CartListResponseVO {
	private List<CartItemVO> cartItems;
}
