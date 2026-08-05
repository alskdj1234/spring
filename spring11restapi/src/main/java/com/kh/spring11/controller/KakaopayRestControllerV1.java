package com.kh.spring11.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.AuthApiResponse;
import com.kh.spring11.configuration.KakaopayProperties;
import com.kh.spring11.service.FlashService;
import com.kh.spring11.service.KakaopayService;
import com.kh.spring11.vo.kakaopay.KakaopayApproveRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayApproveResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayBuyRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayBuyResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResultVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "무식한 결제 API")
@AuthApiResponse

@Slf4j
@RestController
@RequestMapping("/api/kakaopay/v1")
public class KakaopayRestControllerV1 {

	@Autowired
	private KakaopayService kakaopayService;
	
	@Autowired
	private KakaopayProperties kakaopayProperties;
	
	@Autowired
	private FlashService flashService;

	@ApiResponse(responseCode = "200", description = "무식한 결제 성공")
	@PostMapping(value = "/buy", produces = "application/json")
	public KakaopayBuyResponseVO buy(
		@Valid @RequestBody KakaopayBuyRequestVO request,
		@RequestHeader("X-Client-Page") String clientPage
	) {
//		보낼 데이터(Body) 준비
		String partnerOrderId = UUID.randomUUID().toString();
		KakaopayReadyRequestVO payRequest = KakaopayReadyRequestVO.builder()
			.cid(kakaopayProperties.getCid())
			.partnerOrderId(partnerOrderId)
			.partnerUserId("testuser1")
			.itemName(request.getName())
			.totalAmount(request.getPrice())
		.build();
		
		KakaopayReadyResponseVO payResponse = kakaopayService.ready(payRequest);
		
		//결제 승인 요청 매핑에서 필요로 하는 데이터를 FlashService를 이용해서 저장한다
		flashService.addKakaopayReadyFlashData(
			KakaopayReadyResultVO.builder()
				.partnerOrderId(partnerOrderId)//주문 번호
				.partnerUserId(payRequest.getPartnerUserId())//사용자 아이디
				.tid(payResponse.getTid())//거래번호
				.clientPage(clientPage)//돌아갈 주소
			.build()
		);
		
		return KakaopayBuyResponseVO.builder()
					.url(payResponse.getNextRedirectPcUrl())
				.build();
	}
	
	//결제의 결과에 따라 카카오페이가 불러주는 매핑
	@GetMapping("/buy/success/{partnerOrderId}")
	public ResponseEntity<?> success(
		@RequestParam(value = "pg_token") String pgToken,
		@PathVariable String partnerOrderId
	) {
		//(Q) 
		//결제 승인요청을 이 곳에서 해야 하는데 필요한 정보 5개 중 2개가 있다
		//나머지 정보는 어디서 가져오지? FlashService에서 찾아보자!
		KakaopayReadyResultVO result = flashService.getKakaopayReadyFlashData(partnerOrderId);
		
		//모인 데이터들로 승인 요청을 진행
		KakaopayApproveResponseVO payResponse = kakaopayService.approve(
			KakaopayApproveRequestVO.builder()
				.cid(kakaopayProperties.getCid())
				.tid(result.getTid())
				.partnerOrderId(result.getPartnerOrderId())
				.partnerUserId(result.getPartnerUserId())
				.pgToken(pgToken)
			.build()
		);
		
		//return "결제완료!";//임시조치이며, 실제로는 리액트의 완료페이지로 리다이렉트 시켜야함
		return ResponseEntity.status(302)
			//.location(URI.create("http://localhost:5173/pay/v1/buy/success"))
				.location(URI.create(result.getClientPage()+"/success"))
		.build();
	}
	
//	취소나 실패는 카카오페이에 추가 요청할 내용이 없음(결제가 이루어지지 않았으니까)
//	Flash Data만 정리하면 됨
	@GetMapping("/buy/cancel/{partnerOrderId}")
	public ResponseEntity<?> cancel(@PathVariable String partnerOrderId) {
		KakaopayReadyResultVO result = flashService.getKakaopayReadyFlashData(partnerOrderId);
		//할거 없음... 있으면 쓴다
		
		return ResponseEntity.status(302)
				.location(URI.create(result.getClientPage()+"/cancel"))
			.build();
	}
	
	@GetMapping("/buy/fail/{partnerOrderId}")
	public ResponseEntity<?> fail(@PathVariable String partnerOrderId) {
		KakaopayReadyResultVO result = flashService.getKakaopayReadyFlashData(partnerOrderId);
		//할거 없음... 있으면 쓴다
		
		return ResponseEntity.status(302)
				.location(URI.create(result.getClientPage()+"/cancel"))
			.build();
	}
	
}






