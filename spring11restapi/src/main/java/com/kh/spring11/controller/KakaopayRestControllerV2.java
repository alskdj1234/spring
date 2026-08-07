package com.kh.spring11.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.kh.spring11.annotation.CurrentUser;
import com.kh.spring11.dao.PurchaseDao;
import com.kh.spring11.dao.SaleDao;
import com.kh.spring11.dto.PurchaseDetailDto;
import com.kh.spring11.dto.PurchaseDto;
import com.kh.spring11.dto.SaleDto;
import com.kh.spring11.error.GetOutException;
import com.kh.spring11.service.FlashService;
import com.kh.spring11.service.KakaopayService;
import com.kh.spring11.service.PurchaseService;
import com.kh.spring11.service.SaleService;
import com.kh.spring11.vo.jwt.TokenParseResponseVO;
import com.kh.spring11.vo.kakaopay.BuyVO;
import com.kh.spring11.vo.kakaopay.KakaopayApproveRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayApproveResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayBuyRequestVO2;
import com.kh.spring11.vo.kakaopay.KakaopayBuyResponseVO2;
import com.kh.spring11.vo.kakaopay.KakaopayReadyRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResponseVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResultVO2;
import com.kh.spring11.vo.sale.SaleListItemVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
@Tag(name = "실제 상품 구매 API")
@AuthApiResponse

@RestController
@RequestMapping("/api/kakaopay/v2")
public class KakaopayRestControllerV2 {

	@Autowired
	private KakaopayService kakaopayService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private FlashService flashService;
	
	@Autowired
	private PurchaseDao purchaseDao;
	
	@Autowired
	private PurchaseService purchaseService;
	
	@ApiResponse(responseCode = "200", description = "구매 요청 성공")
	@PostMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE)
	public KakaopayBuyResponseVO2 buy(
			@Valid @RequestBody KakaopayBuyRequestVO2 request,
			@RequestHeader("X-Client-Page") String clientPage,
			@CurrentUser TokenParseResponseVO parseVO 
	) {
		//clientPage(현재 구매자가 보고있는 페이지) 추가 처리
		//- ?가 있으면 돌아가는 경로 산정이 어렵다
		//- 혹시라도 clientPage에 ?가 있다면 해당 키워드 뒷부분을 제거한다
		int position = clientPage.indexOf("?");
		if(position >= 0) {//?가 존재한다면
			clientPage = clientPage.substring(0, position);//? 앞부분을 잘라서 설정
		}
		
		//상품명(itemName)과 상품금액(totalAmount)을 계산해야한다
		// - 상품명 : 첫 상품명 + 외 ?건 형태로 작성 (단, 1개만 구매하면 뒷부분은 추가하지 않음)
		// - 상품금액 : 할인가격의 합계
		
		//우리가 가진건 List<BuyVO> 형태 = [ { saleNo : 1 , quantity : 2 }, ... ]
		//넘겨야 할 번호 리스트 = List<Integer> 형태 = [1, 2, ...]
		List<Integer> saleNumbers = request.getOrders()
				.stream()
					.map(order -> order.getSaleNo())
				.toList();
		List<SaleListItemVO> saleList = saleService.findOrders(saleNumbers);
		if(saleList.isEmpty()) throw new GetOutException();//안전장치
		
		//상품명 계산
		String itemName = saleList.get(0).getSaleName();//첫 상품명
		if(saleList.size() >= 2) {//상품이 2개 이상이면
			itemName += " 외 " + (saleList.size()-1) + "건";//추가 개수를 표시
		}
		
		//(+추가) 재고 수 검사
		
		//상품금액 계산
		//- 구매상품 수량정보 (request.getOrders())를 Map으로 만들 필요가 있다
		Map<Integer, Integer> ordersMap = request.getOrders().stream()
		.collect(//재조립,재구성
			Collectors.toMap(
				order -> order.getSaleNo(),//key(상품번호)
				order -> order.getQuantity() //value(구매수량)
			)
		);
		
		//int totalAmount = (saleList에 존재하는 상품 금액 x request.getOrders()의 수량)의 합
		int totalAmount = saleList.stream()
		//.reduce(초기값, 계산함수, 합성방법)
		.reduce(
			0, //0부터 계산을 시작해서
			//acc에 현재 상품의 할인가 * 구매수량을 누적해라
			(acc, cur) -> acc + cur.getSaleDiscountPrice() 
								* ordersMap.get(cur.getSaleNo()) ,
			Integer::sum //최종적으로 얻어내야 하는 결과에 대한 처리 함수 (병렬처리의 합성결과)
			//(a, b) -> a + b
		);
		
		
		//결제 준비요청정보 생성
		//String partnerOrderId = UUID.randomUUID().toString();
		String partnerOrderId = String.valueOf(purchaseDao.purchaseSequence());
		KakaopayReadyRequestVO payRequest = KakaopayReadyRequestVO.builder()
					.partnerOrderId(partnerOrderId)//주문번호
					.partnerUserId(parseVO.getAccountId())//구매자 ID
					.itemName(itemName)
					.totalAmount(totalAmount)
				.build();
		
		//결제 준비 요청
		KakaopayReadyResponseVO payResponse = kakaopayService.ready(payRequest);
		
		//Flash Value 저장
		flashService.addKakaopayReadyFlashData2(
			KakaopayReadyResultVO2.builder()
				.tid(payResponse.getTid())//거래번호
				.partnerOrderId(payRequest.getPartnerOrderId())//주문번호
				.partnerUserId(payRequest.getPartnerUserId())//주문자
				.clientPage(clientPage)//돌아갈 페이지
				.orders(request.getOrders())//주문상품리스트
			.build()
		);
		
		//응답 생성 및 반환 (결제를 위한 카카오페이지)
		return KakaopayBuyResponseVO2.builder()
				.url(payResponse.getNextRedirectPcUrl())
			.build();
	}	
	
	//이 매핑은 카카오에서 요청하는 매핑이므로 리액트와 소통할 때 쓰던 정보를 하나도 못씀
	//@RequestHeader, @CookieValue 등
	@GetMapping("/buy/success/{partnerOrderId}")
	public ResponseEntity<?> success(
		@PathVariable String partnerOrderId,
		@RequestParam(value = "pg_token") String pgToken
	) {
		//승인요청을 위해 준비 단계에서 저장해둔 Flash Value를 불러온다
		KakaopayReadyResultVO2 result = 
					flashService.getKakaopayReadyFlashData2(partnerOrderId);
		
		//결제가 성공했으니 승인요청을 한다
		KakaopayApproveResponseVO payResponse = kakaopayService.approve(
			KakaopayApproveRequestVO.builder()
					.tid(result.getTid())
					.partnerOrderId(result.getPartnerOrderId())
					.partnerUserId(result.getPartnerUserId())
					.pgToken(pgToken)
				.build()
		);
		
		//실 결제가 승인된 뒤 DB에 결제한 상품의 정보를 저장
		purchaseService.save(payResponse, result);
		//상품의 재고 차감
		
		
		
		//React로 리다이렉트
		return ResponseEntity.status(302)
			.location(URI.create(
				result.getClientPage()+"/success/"+result.getPartnerOrderId()
			))
		.build();
	}
	
//	@GetMapping("/buy/cancel/{partnerOrderId}")
//	@GetMapping("/buy/fail/{partnerOrderId}")
	
}





