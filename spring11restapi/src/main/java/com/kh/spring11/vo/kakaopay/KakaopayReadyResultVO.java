package com.kh.spring11.vo.kakaopay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//카카오페이 준비단계에서 승인단계로 넘어갈 데이터
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KakaopayReadyResultVO {
	private String tid;
	private String partnerOrderId;
	private String partnerUserId;
	private String clientPage;
}
