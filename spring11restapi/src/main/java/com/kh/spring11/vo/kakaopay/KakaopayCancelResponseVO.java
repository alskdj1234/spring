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
	private String paymentMethodType;
	private KakaopayAmountVO amount, approvedCancelAmount, canceledAmount, cancelAvailableAmount;
	private String itemName, itemCode;
	private Integer quantity;
	private LocalDateTime createdAt, approvedAt, canceledAt;
	private String payload;
}





