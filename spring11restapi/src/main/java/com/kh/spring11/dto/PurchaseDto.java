package com.kh.spring11.dto;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(name ="결제 대표 정보")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PurchaseDto {
	private int purchaseNo;
	private int purchaseTotal;
	private int purchaseRemain;
	private String purchaseName;
	private String purchaseOwner;
	private String purchaseStatus;
	private String purchaseTid;
	private Timestamp purchaseCtime;
	private Timestamp purchaseUtime;
}
