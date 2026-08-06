package com.kh.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.AuthApiResponse;
import com.kh.spring11.annotation.CurrentUser;
import com.kh.spring11.dao.PurchaseDao;
import com.kh.spring11.dto.PurchaseDetailDto;
import com.kh.spring11.dto.PurchaseDto;
import com.kh.spring11.error.GetOutException;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.service.KakaopayService;
import com.kh.spring11.service.PurchaseService;
import com.kh.spring11.service.SaleService;
import com.kh.spring11.vo.jwt.TokenParseResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayOrderRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayOrderResponseVO;
import com.kh.spring11.vo.purchase.PurchaseHeavyInfoResponseVO;
import com.kh.spring11.vo.purchase.PurchaseInfoResponseVO;
import com.kh.spring11.vo.sale.SaleListItemVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name ="결제 정보 API")
@AuthApiResponse

@RestController
@RequestMapping("/api/purchase")
public class PurchaseRestController {
	@Autowired
	private KakaopayService kakaopayService;
	@Autowired
	private PurchaseDao purchaseDao;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private SaleService saleService;

	@ApiResponse(responseCode = "200", description = "결제조회성공")
	@GetMapping("/simple/{purchaseNo}")
	public PurchaseInfoResponseVO find(@PathVariable int purchaseNo,
			@CurrentUser TokenParseResponseVO parseVO) {
		PurchaseDto purchaseDto = purchaseDao.selectOne(purchaseNo);
		if(purchaseDto == null) throw new TargetNotfoundException();
		if(!purchaseDto.getPurchaseOwner().equals(parseVO.getAccountId())) 
			throw new GetOutException();
		
		//상품정보 추가 조회(구매 원장->구매 상세-> 상품 번호들 추출->조회)
		List<PurchaseDetailDto> purchaseDetailList = purchaseDao.selectDetails(purchaseDto.getPurchaseNo());
		List<Integer> saleNumbers = purchaseDetailList.stream()
				.map(purchaseDetail->purchaseDetail.getPurchaseDetailItem())
				.toList();
		List<SaleListItemVO> sales = saleService.findOrders(saleNumbers);
		
		return PurchaseInfoResponseVO.builder()
				.purchase(purchaseDto)
				.sales(sales)
				.build();
	}
	
	@GetMapping(value ="/heavy/{purchaseNo}", produces = "application/json")
	// 구매 상세 통합 조회
	public PurchaseHeavyInfoResponseVO findHeavyInfo(
			@RequestBody KakaopayOrderRequestVO payRequest,
	        int purchaseNo,
	        TokenParseResponseVO parseVO) {

	    // 1. 구매 기본정보 조회
	    PurchaseDto purchase = purchaseDao.selectOne(purchaseNo);

	    if (purchase == null) {
	        throw new TargetNotfoundException();
	    }

	    // 2. 구매자 본인 확인
	    if (!purchase.getPurchaseOwner().equals(parseVO.getAccountId())) {
	        throw new GetOutException();
	    }

	    // 3. 구매 상세내역 조회
	    List<PurchaseDetailDto> details =
	            purchaseDao.selectDetails(purchaseNo);

	    // 4. 카카오페이 결제정보 조회
	    KakaopayOrderResponseVO payResponse =
	            kakaopayService.order(payRequest,purchase);

	    // 5. 통합 응답 생성
	    return PurchaseHeavyInfoResponseVO.builder()
	            .purchase(purchase)
	            .details(details)
	            .payResponse(payResponse)
	            .build();
	}
	
	
}

