package com.kh.spring11.vo.purchase;

import java.util.List;

import com.kh.spring11.dto.PurchaseDto;
import com.kh.spring11.vo.sale.SaleListItemVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "결제 상세 정보 조회 결과")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PurchaseInfoResponseVO {
	private PurchaseDto purchase;
	private List<SaleListItemVO> sales;
}










