package com.kh.spring11.vo.sale;

import java.util.List;

import com.kh.spring11.dto.AttachDto;
import com.kh.spring11.dto.SaleDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "상품 상세정보 응답 데이터")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SaleDetailResponseVO {
	private SaleDto saleDto;
	private AttachDto thumbnail;
	private List<AttachDto> details; 
}
