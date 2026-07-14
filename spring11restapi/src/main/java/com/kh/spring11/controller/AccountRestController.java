package com.kh.spring11.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.CommonsApiResponse;
import com.kh.spring11.dao.AccountDao;
import com.kh.spring11.dto.AccountDto;
import com.kh.spring11.vo.account.AccountJoinRequestVO;
import com.kh.spring11.vo.account.AccountJoinResponseVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name="회원 정보 관리 서비스")
@CommonsApiResponse
@CrossOrigin
@RestController
@RequestMapping("/api/account")
public class AccountRestController {
	@Autowired
	private AccountDao accountDao;
	
	//회원가입
	@ApiResponse(responseCode = "200", description = "가입 성공")
	@PostMapping(value= "/", produces="application/json")
	public AccountJoinResponseVO join(@RequestBody AccountJoinRequestVO request) {
		//AccountDto에 AccountJoinRequestVO의 데이터를 복사하고 가입처리
		AccountDto accountDto = new AccountDto();
		BeanUtils.copyProperties(request, accountDto);
		
		accountDao.insert(accountDto);
		
		//가입된 결과(모든 데이터가 포함된)를 가져와 응답 정보로 변환해서 반환
		AccountDto resultDto = accountDao.selectOne(accountDto.getAccountId());
		AccountJoinResponseVO response = new AccountJoinResponseVO();
		BeanUtils.copyProperties(resultDto, response);
		return response;
	}
	
	//아이디 중복검사 - 사용 가능하면 true, 불가능하면 false를 반환
		//@GetMapping("/check-id/{accountId}")
		
		//닉네임 중복검사 - 사용 가능하면 true, 불가능하면 false를 반환
		//@GetMapping("/check-nickname/{accountNickname}")
		
		//이메일 중복검사 - 사용 가능하면 true, 불가능하면 false를 반환
		//@GetMapping("/check-email/{accountEmail}")
}
