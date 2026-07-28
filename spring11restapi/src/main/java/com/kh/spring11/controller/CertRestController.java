package com.kh.spring11.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.CommonsApiResponse;
import com.kh.spring11.dao.AccountDao;
import com.kh.spring11.dao.CertDao;
import com.kh.spring11.dto.AccountDto;
import com.kh.spring11.dto.CertDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.service.EmailService;
import com.kh.spring11.service.RandomService;
import com.kh.spring11.vo.cert.CertCheckRequestVO;
import com.kh.spring11.vo.cert.CertCheckResponseVO;
import com.kh.spring11.vo.cert.CertSendRequestVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

@Tag(name = "이메일 발송 서비스")
@CommonsApiResponse

@RestController
@RequestMapping("/service/cert")
public class CertRestController {
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CertDao certDao;
	
	@Autowired
	private RandomService randomService;
	
	
	@Autowired
	private AccountDao accountDao;
	@ApiResponse(responseCode = "200", description = "이메일 발송 성공")
	@PostMapping("/send")
	public void send(@RequestBody CertSendRequestVO vo) throws MessagingException, IOException {
		emailService.sendCertNumber2(vo.getCertEmail());
	}
	
	@ApiResponse(responseCode = "200", description = "이메일 검사 성공")	
	@PostMapping(value = "/check", produces = "application/json")
	public CertCheckResponseVO check(@RequestBody CertCheckRequestVO vo) {
		//1. 정보가 있는지 확인
		CertDto findDto = certDao.find(vo.getCertEmail());
		//if(findDto == null) throw new WhoAreYouException();
		if(findDto == null) {
			return CertCheckResponseVO.builder()
						.valid(false)
					.build();
		}
		
		//2. 번호가 맞는지 확인
		boolean valid = vo.getCertNumber().equals(findDto.getCertNumber());
		if(valid == false) {
			return CertCheckResponseVO.builder()
					.valid(false)
				.build();
		}
		
		//3. 시간이 유효한지 확인
		LocalDateTime current = LocalDateTime.now();//현재시각
		LocalDateTime sent = findDto.getCertTime().toLocalDateTime();//발송시각
		Duration duration = Duration.between(sent, current);
		if(duration.toMinutes() > 10) {//10분이 지났어?
			return CertCheckResponseVO.builder()
					.valid(false)
				.build();
		}
		
		//4. 인증 가능한 상태인지 확인 (cert_yn = 'N')
		//if(findDto.getCertYn().equals("Y")) {
		if(findDto.isComplete()) {
			return CertCheckResponseVO.builder()
					.valid(false)
				.build();
		}
		
		
		//certDao.delete(vo.getCertEmail());//인증기록 삭제
		certDao.use(vo.getCertEmail());//인증완료(cert_yn='Y')로 업데이트
		return CertCheckResponseVO.builder()
				.valid(true)
			.build();
	}
	
	
}








