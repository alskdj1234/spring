package com.kh.spring11.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.CommonsApiResponse;
import com.kh.spring11.dao.AccountDao;
import com.kh.spring11.dto.AccountDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.vo.account.AccountFindResponseVO;
import com.kh.spring11.vo.account.AccountJoinRequestVO;
import com.kh.spring11.vo.account.AccountJoinResponseVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "회원 정보 관리 서비스")
@CommonsApiResponse

@CrossOrigin
@RestController
@RequestMapping("/api/account")
public class AccountRestController {
	@Autowired
	private AccountDao accountDao;
	
	//회원가입
	@ApiResponse(responseCode = "200", description = "가입 성공")
	@PostMapping(value = "/", produces = "application/json")
	public AccountJoinResponseVO join(
			@RequestBody AccountJoinRequestVO request) {
		//AccountDto에 AccountJoinRequestVO의 데이터를 복사하고 가입 처리
		AccountDto accountDto = new AccountDto();
		BeanUtils.copyProperties(request, accountDto);//request → accountDto
		accountDao.insert(accountDto);
		//가입된 결과(모든 데이터가 포함된)를 가져와서 응답 정보로 변환하여 반환
		AccountDto resultDto = accountDao.selectOne(accountDto.getAccountId());
		AccountJoinResponseVO response = new AccountJoinResponseVO();
		BeanUtils.copyProperties(resultDto, response);
		return response;
	}
	
	//아이디 중복검사 - 사용 가능하면 true, 불가능하면 false를 반환
	@GetMapping("/check-id/{accountId}")
	public boolean checkAccountId(@PathVariable String accountId) {
		return accountDao.checkAvailableId(accountId);
	}
	//닉네임 중복검사 - 사용 가능하면 true, 불가능하면 false를 반환
	@GetMapping("/check-nickname/{accountNickname}")
	public boolean checkAccountNickname(@PathVariable String accountNickname) {
		return accountDao.checkAvailableNickname(accountNickname);
	}
	//이메일 중복검사 - 사용 가능하면 true, 불가능하면 false를 반환
	@GetMapping("/check-email/{accountEmail}")
	public boolean checkAccountEmail(@PathVariable String accountEmail) {
		return accountDao.checkAvailableEmail(accountEmail);
	}
	
	//회원 정보 반환 매핑 이지만 내 정보 아님
	@ApiResponse(responseCode= "200", description = "조회 성공")
	@GetMapping(value="/{accountId}", produces ="application/json")
	public AccountFindResponseVO find(@PathVariable String accountId) {
		AccountDto accountDto = accountDao.selectOne(accountId);
		if(accountDto == null) throw new TargetNotfoundException();
		
		AccountFindResponseVO response = new AccountFindResponseVO();
		BeanUtils.copyProperties(accountDto, response);//가능한 항목 복사
		return response;
	}
}