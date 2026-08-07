package com.kh.spring11.vo.kakaopay;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
//직렬화(serialize)와 역직렬화(deserialize)에 모두 관여
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaopayOrderResponseVO {
	//다음 둘 중 하나를 적어서 "수신"에만 적용되도록 구현
	//이 어노테이션들은 역직렬화(수신)에만 관여한다
	//@JsonAlias("별칭") - 원래이름으로 받을 수 없는 경우 대체할 별칭을 부여
	//@JsonSetter("이름") - 원래이름 대신 사용할 이름을 지정
	@JsonAlias("tid")
	private String tid;//결제고유번호
	@JsonAlias("cid")
	private String cid;//가맹점코드
	@JsonAlias("status")
	private String status;//결제 상태
	@JsonAlias("partner_order_id")
	private String partnerOrderId;//가맹점 내 주문번호
	@JsonAlias("partner_user_id")
	private String partnerUserId;//가맹점 내 주문자ID
	@JsonAlias("payment_method_type")
	private String paymentMethodType;//결제수단(CARD or MONEY)
	@JsonAlias("amount")
	private KakaopayAmountVO amount;//결제 금액
	@JsonAlias("canceled_amount")
	private KakaopayAmountVO canceledAmount;//결제 금액
	@JsonAlias("cancel_available_amount")
	private KakaopayAmountVO cancelAvailableAmount;//결제 금액
	@JsonAlias("item_name")
	private String itemName;//상품 이름
	@JsonAlias("item_code")
	private String itemCode;//상품 코드
	@JsonAlias("quantity")
	private Integer quantity;//상품 수량(1로 고정)
	@JsonAlias("created_at")
	private LocalDateTime createdAt;//결제 시작시각
	@JsonAlias("approved_at")
	private LocalDateTime approvedAt;//결제 승인시각
	@JsonAlias("canceled_at")
	private LocalDateTime canceledAt;//결제 취소시각
	@JsonAlias("selected_card_info")
	private KakaopaySelectedCardInfoVO selectedCardInfo;//결제 카드 정보
	@JsonAlias("payment_action_details")
	private List<KakaopayPaymentActionDetailVO> paymentActionDetails;//결제 상세내역
//	private KakaopayPaymentActionDetail[] paymentActionDetails;//결제 상세내역
}
