package com.kh.spring11.service;

import com.kh.spring11.vo.kakaopay.KakaopayApproveResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResultVO2;

public interface PurchaseService {
	void save(
			
			KakaopayApproveResponseVO payResponse,
			KakaopayReadyResultVO2 result
			);
}
