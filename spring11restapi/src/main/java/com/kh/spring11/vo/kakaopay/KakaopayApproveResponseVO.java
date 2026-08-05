package com.kh.spring11.vo.kakaopay;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaopayApproveResponseVO {
	private String aid;//요청 고유번호
	private String tid;//결제 고유번호
	private String cid;//가맹점 코드
	private String sid;//정기결제용 ID (정기결제시에만 생김)
	private String partnerOrderId;//주문번호(가맹점내)
	private String partnerUserId;//주문자(가맹점내)
	private String paymentMethodType;//결제수단(CARD/MONEY)
	private KakaopayAmountVO amount;//결제금액정보
	private KakaopayCardInfoVO cardInfo;//카드정보
	private String itemName;//상품명
	private String itemCode;//상품코드
	private Integer quantity;//상품수량
	private LocalDateTime createdAt;//생성시각
	private LocalDateTime approvedAt;//승인시각
	private String payload;//추가 요청사항
}











