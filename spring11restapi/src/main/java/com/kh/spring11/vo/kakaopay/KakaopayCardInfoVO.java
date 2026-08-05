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
public class KakaopayCardInfoVO {
	private String kakaopayPurchaseCorp;//매입사명
	private String kakaopayPurchaseCorpCode;//매입사코드
	private String kakaopayIssuerCorp;//발급사명
	private String kakaopayIssuerCorpCode;//발급사코드
	private String bin;//카드BIN
	private String cardType;//카드 유형
	private String installMonth;//할부개월
	private String approvedId;//승인번호
	private String cardMid;//가맹점번호
	private String interestFreeInstall;//무이자(Y/N)
	private String installmentType;//할부유형
	private String cardItemCode;//카드 상품코드
}












