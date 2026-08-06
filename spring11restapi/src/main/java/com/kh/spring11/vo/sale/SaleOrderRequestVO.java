package com.kh.spring11.vo.sale;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
@Schema(name = "주문 상품 조회 요청 정보")
@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class SaleOrderRequestVO {
	@NotEmpty
	private List<Integer> saleNumbers;
}
