package com.kh.spring11.vo.kakaopay;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaopayCancelResponseVO {
	private String aid, tid, cid, status;
	private String partnerOrderId, partnerUserId;
	private String paymentMenthodType;
	private String itemName, itemCode;
	private String payload;
	private Integer quantity;
	private KakaopayAmountVO amount, approvedCancelAmount, canceledAmount, cancelTaxFreeAmount;
	private LocalDateTime createdAt, approvedAt, canceledAt;
}
