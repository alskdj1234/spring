package com.kh.spring11.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name="결제 상세 정보")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PurchaseDetailDto {
	  private int purchaseDetailNo;
	    private int purchaseDetailOrigin;
	    private int purchaseDetailItem;
	    private String purchaseDetailName;
	    private int purchaseDetailPrice;
	    private int purchaseDetailQty;
	    private String purchaseDetailStatus;
}
