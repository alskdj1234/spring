package com.kh.spring11.dto;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "SALE 테이블 DTO")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SaleDto {
	private int saleNo;
	private String saleName;
	private String saleCategory;
	private int saleOriginalPrice;
	private int saleDiscountPrice;
	private Timestamp saleRegTime;
	private String saleContent;
	private int saleStock;
}