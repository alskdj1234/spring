package com.kh.spring11.vo.kakaopay;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KakaopayReadyResultVO2 {
	private String tid;
	private String partnerOrderId;
	private String partnerUserId;
	private String clientPage;
	
	//버전 1에 구매한 상품의 정보가 추가로
	
	private List<BuyVO> orders;
	
}
