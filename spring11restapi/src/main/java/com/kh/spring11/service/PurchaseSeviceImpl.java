package com.kh.spring11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring11.annotation.CurrentUser;
import com.kh.spring11.dao.PurchaseDao;
import com.kh.spring11.dao.SaleDao;
import com.kh.spring11.dto.PurchaseDetailDto;
import com.kh.spring11.dto.PurchaseDto;
import com.kh.spring11.dto.SaleDto;
import com.kh.spring11.error.GetOutException;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.vo.jwt.TokenParseResponseVO;
import com.kh.spring11.vo.kakaopay.BuyVO;
import com.kh.spring11.vo.kakaopay.KakaopayApproveResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayCancelRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayCancelResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResultVO2;
@Service
public class PurchaseSeviceImpl implements PurchaseService {
	@Autowired
	private PurchaseDao purchaseDao;
	@Autowired
	private SaleDao saleDao;
	@Autowired
	private KakaopayService kakaopayService;
	
	@Transactional
	@Override
	public void save(KakaopayApproveResponseVO payResponse, KakaopayReadyResultVO2 result) {
		//[1] 대표정보 등록 (번호는 준비단계에서 만들어서 partnerOrderId에 문자열 형태로 넣어뒀음)
				int purchaseNo = Integer.parseInt(payResponse.getPartnerOrderId());
				purchaseDao.purchaseInsert(
					PurchaseDto.builder()
						.purchaseNo(purchaseNo)
						.purchaseName(payResponse.getItemName())//상품명
						.purchaseTotal(payResponse.getAmount().getTotal())//구매금액
						.purchaseRemain(payResponse.getAmount().getTotal())//환불가능금액(=구매금액과 동일)
						.purchaseOwner(payResponse.getPartnerUserId())//구매자
						.purchaseTid(payResponse.getTid())//거래번호
					.build()
				);
				
				//[2] 상세정보 등록
				List<BuyVO> orders = result.getOrders();
				for(BuyVO order : orders) {
					int purchaseDetailNo = purchaseDao.purchaseDetailSequence();
					SaleDto saleDto = saleDao.selectOne(order.getSaleNo());//상품정보 조회
					purchaseDao.purchaseDetailInsert(
						PurchaseDetailDto.builder()
							.purchaseDetailNo(purchaseDetailNo)//상세번호
							.purchaseDetailOrigin(purchaseNo)//대표번호
							.purchaseDetailItem(order.getSaleNo())//상품번호
							.purchaseDetailName(saleDto.getSaleName())//상품명 스냅샷
							.purchaseDetailPrice(saleDto.getSaleDiscountPrice())//상품가격 스냅샷
							.purchaseDetailQty(order.getQuantity())//수량
						.build()
					);
				}
		
	}

	@Override
	public KakaopayCancelResponseVO cancelUnit(int purchaseDetailNo) {
		//구매 내역 조회 
		
		PurchaseDto find = purchaseDao.selectOne(purchaseDetailNo);
		return null;
	}
	@Transactional
	@Override
	public KakaopayCancelResponseVO cancelAll(int purchaseNo, TokenParseResponseVO parseVO) {
		
		
		
		PurchaseDto find = purchaseDao.selectOne(purchaseNo);
		
		if(!find.getPurchaseOwner().equals(parseVO.getAccountId())) throw new GetOutException();
		
		if(find == null) throw new TargetNotfoundException();
		if(find.getPurchaseStatus().equals("전체취소"))
				throw new GetOutException();
		if(find.getPurchaseStatus().equals("차단"))
			throw new GetOutException();
		if(find.getPurchaseRemain() == 0)
			throw new GetOutException();
	
		//db처리
		
		purchaseDao.purchaseCancel(purchaseNo);
		
		KakaopayCancelResponseVO payResponse = kakaopayService.cancel(
				
				KakaopayCancelRequestVO.builder()
					.tid(find.getPurchaseTid())
					.cancelAmount(find.getPurchaseRemain())
					.build()
				);
	
		return payResponse;
	}

	

}
