package com.kh.spring11.vo.kakaopay;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//카카오페이 준비단계에서 승인단계로 넘어갈 데이터
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KakaopayReadyResultVO2 {
	private String tid;
	private String partnerOrderId;
	private String partnerUserId;
	private String clientPage;
	//version 2에서는 구매한 상품의 정보가 추가로 넘겨져야함 (카카오페이가 아닌 DB 처리를 위한 데이터)
	private List<BuyVO> orders;
}
