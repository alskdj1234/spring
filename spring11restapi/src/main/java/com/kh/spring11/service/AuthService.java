package com.kh.spring11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.spring11.dao.AccountDao;
import com.kh.spring11.dto.AccountDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.vo.auth.AuthLoginRequestVO;
import com.kh.spring11.vo.auth.AuthLoginResponseVO;

//인증과 관련된 복잡한 작업들을 모듈화 하여 처리하기 위한 서비스
@Service
public class AuthService {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//로그인 처리
	public AuthLoginResponseVO login(AuthLoginRequestVO request) {
		//아이디에 해당하는 회원 조회
		AccountDto accountDto = accountDao.selectOne(request.getAccountId());
		if(accountDto == null) throw new TargetNotfoundException();
		
		//비밀번호 비교
		boolean valid = passwordEncoder.matches(
				request.getAccountPassword(), 
				accountDto.getAccountPassword()
		);
		if(valid == false) throw new TargetNotfoundException();
		
		//로그인 성공
		return AuthLoginResponseVO.builder()
				.accountId(accountDto.getAccountId())//회원아이디
				.accountLevel(accountDto.getAccountLevel())//회원레벨
				.accountNickname(accountDto.getAccountNickname())//회원닉네임
			.build();
	}
}






