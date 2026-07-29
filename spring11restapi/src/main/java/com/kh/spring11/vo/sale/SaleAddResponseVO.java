package com.kh.spring11.vo.sale;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "상품 등록 완료 후 응답 데이터")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SaleAddResponseVO {
	private int saleNo;
	private String saleName;
	private String saleCategory;
	private int saleOriginalPrice;
	private int saleDiscountPrice;
	private Timestamp saleRegTime;
	private String saleContent;
	private int saleStock;
}
