package com.kh.spring11.vo.kakaopay;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaopayPaymentActionDetailVO {
	@JsonAlias("aid")
	private String aid;//요청고유번호
	@JsonAlias("approved_at")
	private LocalDateTime approvedAt;//거래시각
	@JsonAlias("amount")
	private Integer amount;//총 금액
	@JsonAlias("point_amount")
	private Integer pointAmount;//총 포인트
	@JsonAlias("discount_amount")
	private Integer discountAmount;//총 할인 금액
	@JsonAlias("green_deposit")
	private Integer greenDeposit;//컵 보증금
	@JsonAlias("payment_action_type")
	private String paymentActionType;//결제 타입(PAYMENT-결제, CANCEL-취소, ISSUED_SID-SID발급 중 하나)
	@JsonAlias("payload")
	private String payload;//요청에 첨부된 추가 데이터(메모)
}
