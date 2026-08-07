package com.kh.spring11.vo.kakaopay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Schema(name = "상품 1개의 번호와 구매 수량")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BuyVO {
	@NotNull @Positive
	private int saleNo;
	@NotNull @Positive
	private int quantity;
}
