package com.kh.spring11.vo.purchase;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "장바구니 목록 결과 데이터")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CartListResponseVO {
	List<CartItemVO> cartItems;
}
