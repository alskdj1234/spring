package com.kh.spring11.vo.kakaopay;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaopayAmountVO {
	@JsonAlias("total")
	private Integer total;
	@JsonAlias("tax_free")
	private Integer taxFree;
	@JsonAlias("vat")
	private Integer vat;
	@JsonAlias("point")
	private Integer point;
	@JsonAlias("discount")
	private Integer discount;
	@JsonAlias("green_deposit")
	private Integer greenDeposit;
}











