package com.kh.spring11.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.kh.spring11.vo.kakaopay.KakaopayReadyResultVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResultVO2;

//임시 데이터를 저장하기 위한 서비스
@Service
public class FlashService {

//	Map에 데이터를 저장해두고 이를 이름으로 찾아갈 수 있도록 처리
//	- 이름을 뭘로 할것인가? → String partnerOrderId
//	- 값을 뭘로 할 것인가?	 → KakaopayReadyResultVO (partnerOrderId, partnerUserId, tid)
	
//	카카오페이 Version 1 용도의 플래시 데이터 저장소
//	private Map<String, KakaopayReadyResultVO> kakaopayReadyFlashMap = new HashMap<>();//절대 안됨 (무결성을 보장할 수 없음)
//	private Map<String, KakaopayReadyResultVO> kakaopayReadyFlashMap = 
//								Collections.synchronizedMap(new HashMap<>());
	private Map<String, KakaopayReadyResultVO> kakaopayReadyFlashMap =
													new ConcurrentHashMap<>();
	
	public void addKakaopayReadyFlashData(KakaopayReadyResultVO result) {
		kakaopayReadyFlashMap.put(result.getPartnerOrderId(), result);
	}
	public KakaopayReadyResultVO getKakaopayReadyFlashData(String partnerOrderId) {
		return kakaopayReadyFlashMap.remove(partnerOrderId);
	}
	
//	카카오페이 Version 2 용도의 플래시 데이터 저장소	
	private Map<String, KakaopayReadyResultVO2> kakaopayReadyFlashMap2 =
												new ConcurrentHashMap<>();

	public void addKakaopayReadyFlashData2(KakaopayReadyResultVO2 result) {
		kakaopayReadyFlashMap2.put(result.getPartnerOrderId(), result);
	}
	public KakaopayReadyResultVO2 getKakaopayReadyFlashData2(String partnerOrderId) {
		return kakaopayReadyFlashMap2.remove(partnerOrderId);
	}
}








