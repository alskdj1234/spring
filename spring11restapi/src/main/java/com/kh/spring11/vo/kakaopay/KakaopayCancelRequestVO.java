package com.kh.spring11.vo.kakaopay;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KakaopayCancelRequestVO {
 private String cid;
 private String tid;

 private int cancelAmount;
 @Builder.Default
 private int cancelTaxFreeAmount = 0;
 
}
