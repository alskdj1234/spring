package com.kh.spring11.vo.sale;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "상품 정보 수정 요청 데이터")
@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class SaleEditRequestVO {
	@NotNull
	private String saleName;
	@NotNull
	private String saleCategory;
	@NotNull @PositiveOrZero
	private int saleOriginalPrice;
	
	private Integer saleDiscountPrice;
	
	private String saleContent;
	
	@NotNull @PositiveOrZero
	private int saleStock;
}
