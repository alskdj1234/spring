package com.kh.spring11.vo.kakaopay;

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
public class KakaopaySelectedCardInfoVO {
	@JsonAlias("card_bin")
	private String cardBin;//카드BIN
	@JsonAlias("install_month")
	private Integer installMonth;//할부 개월 수
	@JsonAlias("installment_type")
	private String installmentType;//할부 유형(업종 무이자/분담 무이자)
	@JsonAlias("card_corp_name")
	private String cardCorpName;//카드사 정보
	@JsonAlias("interest_free_install")
	private String interestFreeInstall;//무이자할부(Y/N)
}
