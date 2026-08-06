package com.kh.spring11.service;

import com.kh.spring11.vo.jwt.TokenParseResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayApproveResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayCancelResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResultVO2;

public interface PurchaseService {
	void save(
			
			KakaopayApproveResponseVO payResponse,
			KakaopayReadyResultVO2 result
			);

	KakaopayCancelResponseVO cancelUnit(int purchaseDetailNo);



	KakaopayCancelResponseVO cancelAll(int purchaseNo, TokenParseResponseVO parseVO);
}
