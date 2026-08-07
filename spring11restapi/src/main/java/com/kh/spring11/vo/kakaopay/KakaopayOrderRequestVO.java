package com.kh.spring11.vo.kakaopay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KakaopayOrderRequestVO {
	private String cid;
	private String tid;
}
