package com.kh.spring11.service;

import com.kh.spring11.vo.jwt.TokenParseResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayApproveResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayCancelResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResultVO2;

public interface PurchaseService {
	void save(
		KakaopayApproveResponseVO payResponse,//승인 완료 후 카카오페이의 응답 정보
		KakaopayReadyResultVO2 result//준비단계의 Flash Value
	);

	KakaopayCancelResponseVO cancelAll(int purchaseNo, TokenParseResponseVO parseVO);
	KakaopayCancelResponseVO cancelUnit(int purchaseDetailNo, TokenParseResponseVO parseVO);

}
