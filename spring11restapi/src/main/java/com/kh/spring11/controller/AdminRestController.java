package com.kh.spring11.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.AuthApiResponse;
import com.kh.spring11.dao.AccountDao;
import com.kh.spring11.dao.AdminDao;
import com.kh.spring11.dto.AccountDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.service.EmailService;
import com.kh.spring11.service.JwtService;
import com.kh.spring11.service.RandomService;
import com.kh.spring11.vo.account.AccountBlockResponseVO;
import com.kh.spring11.vo.account.AccountFindResponseVO;
import com.kh.spring11.vo.account.AccountSearchRequestVO;
import com.kh.spring11.vo.account.AccountSearchResponseVO;
import com.kh.spring11.vo.account.AccountSearchResultVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

@Tag(name ="관리자 APi")
@AuthApiResponse
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
 @Autowired
 private AdminDao adminDao;
 @Autowired
 private AccountDao accountDao;
 @Autowired
 private JwtService jwtService;
 
 @Autowired
 private EmailService emailService;
 
 @Autowired
 private RandomService randomService;

//회원 정보를 반환하는 매핑(주의 : 내정보 아님)
	@ApiResponse(responseCode = "200", description = "조회 성공")
	@GetMapping(value = "/{accountId}", produces = "application/json")
	public AccountFindResponseVO find(@PathVariable String accountId) {
		AccountDto accountDto = accountDao.selectOne(accountId);
		if(accountDto == null) throw new TargetNotfoundException();
		
		AccountFindResponseVO response = new AccountFindResponseVO();
		BeanUtils.copyProperties(accountDto, response);//가능한 항목 복사
		return response;
	}
	@ApiResponse(responseCode = "200", description ="검색 성공")
	@PostMapping(value="/search", produces="application/json")
	public AccountSearchResponseVO search(
		/*@Valid*/@RequestBody AccountSearchRequestVO request
	) {
		//목록 조회
		List<AccountSearchResultVO> list = accountDao.search(request);
		
		//카운트 조회//
		int count = accountDao.count(request);
		
		//최종 응답
		return AccountSearchResponseVO.builder()
					.list(list)
					.last(count <= list.size())
				.build();
	}

	@ApiResponse(responseCode = "200", description ="차단/해제 성공")
 @PatchMapping(value="/block/{accountId}", produces="application/json")
 public AccountBlockResponseVO block(//@RequestBody AccountBlockRequestVO request,
		@PathVariable String accountId) {
	 AccountDto accountDto = accountDao.selectOne(accountId);
	 if(accountDto == null) throw new TargetNotfoundException();
	 boolean current =  accountDto.getAccountBlock().equals("Y");
	 
	 accountDto.setAccountBlock(
			current ? "N" : "Y"
			 );
	 accountDao.updateAccountBlock(accountDto);

	 AccountBlockResponseVO response = new AccountBlockResponseVO();
	 BeanUtils.copyProperties(accountDto, response);
	 return response;
 }
 
	@ApiResponse(responseCode = "200", description = "변경 메일 발송 성공")
	@PostMapping("/tempPassword/{accountId}")
	public void tempPassword(@PathVariable  String accountId) throws MessagingException, IOException {
		AccountDto accountDto = accountDao.selectOne(accountId);
		if(accountDto == null) throw new TargetNotfoundException();
		
		//[1] 임시 비밀번호를 발행
		String randomPassword = randomService.generateString(12);
		
		//[2] db 변경
		accountDao.updateAccountPassword(AccountDto.builder()
				.accountId(accountId)
				.accountPassword(randomPassword)
				.build());
		
		//[3] 이메일 발송
		emailService.sendTempPassword(accountDto.getAccountEmail(), randomPassword);
	}
 
// 
// @PostMapping(value="/users", produces = "application/json")
// public List<AdminComplSearchResponseVO> complSearch(@RequestBody AdminComplSearchRequestVO request) {
//	 return adminDao.complexSearch(request);
// }
}
