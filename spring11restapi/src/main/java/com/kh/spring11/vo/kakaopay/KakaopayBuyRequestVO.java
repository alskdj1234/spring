package com.kh.spring11.vo.kakaopay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Schema(name = "무식하게 구매할 때의 요청 VO")
@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class KakaopayBuyRequestVO {
	@NotNull
	private String name;
	@NotNull @Positive
	private int price;
}
