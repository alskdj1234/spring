package com.kh.spring11.vo.sale;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name ="상품 정보 등록용 데이터")
@Data @JsonIgnoreProperties(ignoreUnknown = true)
@Builder @NoArgsConstructor @AllArgsConstructor
public class SaleAddRequestVO2 {
	@NotNull
	private String saleName;
	private String saleCategory;
	@NotNull @PositiveOrZero
	private Integer saleOriginalPrice;
	@PositiveOrZero
	private Integer saleDiscountPrice;
	
	private String saleContent;
	@NotNull @PositiveOrZero
	private Integer saleStock;
	}
