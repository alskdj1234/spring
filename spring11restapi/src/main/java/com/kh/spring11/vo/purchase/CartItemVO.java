package com.kh.spring11.vo.purchase;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "장바구니 내의 상품 1개 정보")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CartItemVO {
	private int  no ;
	private int  origin, discount ;
	private int  qty ;
	private Integer  thumbnail ;
	private String name;
	private String category;
	private Timestamp time;
	
}
