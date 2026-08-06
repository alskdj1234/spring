package com.kh.spring11.vo.kakaopay;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaopayPaymentActionDetailVO {
	private Integer amount;
	@JsonAlias("point_amount")
	private Integer pointAmount;
	@JsonAlias("discount_amount")
	private Integer discountAmount;
	@JsonAlias("green_deposit")
	private Integer greenDeposit;
	private String aid;
	@JsonAlias("payment_action_type")
	private String paymentActionType;
	private String payload;
	@JsonAlias("approved_at")
	private LocalDateTime approvedAt;
}
