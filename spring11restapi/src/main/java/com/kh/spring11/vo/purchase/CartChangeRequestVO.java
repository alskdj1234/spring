package com.kh.spring11.vo.purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Schema(name = "장바구니 수량 변경 요청 데이터")
@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class CartChangeRequestVO {
	@NotNull @Positive private int no;
	@NotNull @Positive private int qty;
}






