package com.kh.spring11.vo.kakaopay;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//뭐라고 써야 내가 카멜케이스로 쓰는 항목이 자동으로 스네이크 케이스로 변할까?
//Jackson의 @JsonNaming을 사용하여 해결 (변환 시 자동으로 바뀜)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KakaopayReadyRequestVO {
	private String cid;
	private String partnerOrderId;
	private String partnerUserId;
	private String itemName;
	@Builder.Default
	private int quantity = 1;
	private long totalAmount;
	private long taxFreeAmount;
	private String approvalUrl;
	private String cancelUrl;
	private String failUrl;
}






