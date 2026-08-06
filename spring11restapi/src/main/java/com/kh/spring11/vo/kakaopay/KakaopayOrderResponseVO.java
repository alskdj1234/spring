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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaopayOrderResponseVO {
	
//	@JsonAlias("별칭") => 원이름으로 받을 수 없는 경우 "대체" 별칭을 부여
//	@JsonSetter("이름") => 원이름 대신 사용할 이름 "지정"
// 수신에'만' 적용, 역직렬화(수신)에만 관여	
	@JsonAlias("cid")
	private String cid;

	@JsonAlias("tid")
	private String tid;

	@JsonAlias("status")
	private String status;

	@JsonAlias("partner_order_id")
	private String partnerOrderId;

	@JsonAlias("partner_user_id")
	private String partnerUserId;

	@JsonAlias("payment_method_type")
	private String paymentMethodType;

	@JsonAlias("amount")
	private KakaopayAmountVO amount;

	@JsonAlias("canceled_amount")
	private KakaopayAmountVO cancelAmount;

	@JsonAlias("cancel_available_amount")
	private KakaopayAmountVO cancelAvailabelAmount;

	@JsonAlias("item_name")
	private String itemName;

	@JsonAlias("item_code")
	private String itemCode;

	@JsonAlias("quantity")
	private Integer quanttity;

	@JsonAlias("created_at")
	private LocalDateTime createdAt;

	@JsonAlias("approved_at")
	private LocalDateTime approvedAt;

	@JsonAlias("canceled_at")
	private LocalDateTime canceledAt;

	@JsonAlias("selected_card_info")
	private KakaopaySelectedCardInfoVO selectedCardInfo;

	@JsonAlias("payment_action_details")
	private List<KakaopayPaymentActionDetailVO> paymentActionDetails;
}

