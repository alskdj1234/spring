package com.kh.spring11.vo.sale;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "상품목록 요청 데이터")
@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class SaleListRequestVO {
	private String saleCategory;
}
