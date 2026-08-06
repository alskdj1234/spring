package com.kh.spring11.vo.purchase;

import java.util.List;

import com.kh.spring11.dto.PurchaseDetailDto;
import com.kh.spring11.dto.PurchaseDto;
import com.kh.spring11.vo.kakaopay.KakaopayOrderResponseVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(name="결제 번호로 조회 가능한 모든 정보")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PurchaseHeavyInfoResponseVO {
 private PurchaseDto purchase;
 private List<PurchaseDetailDto> details;
 private KakaopayOrderResponseVO payResponse;
}
