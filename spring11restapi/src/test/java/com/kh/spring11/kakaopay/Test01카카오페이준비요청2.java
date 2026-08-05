package com.kh.spring11.kakaopay;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test01카카오페이준비요청2 {
	
	//@Autowired만 적으면 동일한 요소가 애플리케이션에 2개 이상이 있을 경우 오류가 발생
	//추가로 @Qualifier를 적어서 아이디를 지정하여 주입한다
	@Qualifier("kakaopayClient")
	@Autowired
	private WebClient webClient;
	
	@Test
	public void test() {
//		상세 주소 설정
		String url = "/online/v1/payment/ready";
		
//		보낼 데이터(Body) 준비
		Map<String, String> body = new HashMap<>();
		body.put("cid", "TC0ONETIME");//가맹점 코드
		body.put("partner_order_id", UUID.randomUUID().toString());//가맹점 주문번호 (주문을 구분하기 위한 고유번호)
		body.put("partner_user_id", "testuser1");//가맹점 회원의 고유ID (=account_id , 구매자 식별키)
		body.put("item_name", "이모티콘 10종세트 외 2건");//상품의 이름 (결제할 때 뜨는 이름)
		body.put("quantity", "1");//상품수량 (1로 통일하고 DB에서 관리)
		body.put("total_amount", "3000");//결제금액 (상품이 여러개면 합산한 금액)
		body.put("tax_free_amount", "0");//비과세액 (면세점인 경우 해당)
//		아래 주소들은 반드시 카카오페이 개발자센터 플랫폼에 등록된 주소로 시작해야 한다
		body.put("approval_url", "http://localhost:8080/success");//성공 시 연락받을 페이지 주소
		body.put("cancel_url", "http://localhost:8080/cancel");//취소 시 연락받을 페이지 주소
		body.put("fail_url", "http://localhost:8080/fail");//실패 시 연락받을 페이지 주소
		
//		요청 발송 및 응답 수신
		Map response = webClient.post()//POST요청
				.uri(url)//상세주소
				.bodyValue(body)//첨부데이터
			.retrieve()//응답 수신 허용
				.bodyToMono(Map.class)//일시불(Mono)로 수신 (↔ 할부는 Flux)
				.block();//동기방식으로 수신
		
//		결과 확인
		log.debug("partner_order_id = {}", body.get("partner_order_id"));
		log.debug("partner_user_id = {}", body.get("partner_user_id"));
		for(Object key : response.keySet()) {
			Object value = response.get(key);
			log.debug("{} = {}", key, value);
		}
	}
}









