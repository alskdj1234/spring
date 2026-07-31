package com.kh.spring11.vo.sale;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "상품 목록 조회 정보")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SaleListItemVO {
	private int saleNo;
	private String saleName;
	private String saleCategory;
	private int saleOriginalPrice;
	private int saleDiscountPrice;
	private Integer attachNo;//없을 수 있음
}









