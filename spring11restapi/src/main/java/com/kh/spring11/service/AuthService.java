package com.kh.spring11.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.spring11.configuration.LoginProperties;
import com.kh.spring11.dao.AccountDao;
import com.kh.spring11.dto.AccountDto;
import com.kh.spring11.error.GetOutException;
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
	@Autowired
	private LoginProperties loginProperties;
	
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
		//차단된 회원이면..403반환
		if(accountDto.getAccountBlock().equals("Y")) {
			throw new GetOutException("나아아아가");
		}
		//비밀번호 변경이 오래 됐는지 판정
		//설정 파일의 need-update-term 보다 변경일이 오래되어야 함.(초과 됨)
		
		Timestamp lastChange = accountDto.getAccountChange();
		
		
		if(lastChange == null) {//한번도 바꾼적이 없는 경우(가입일이랑 비교)
			lastChange = accountDto.getAccountJoin();
		}
		LocalDateTime lastTime = lastChange.toLocalDateTime();
		LocalDateTime current = LocalDateTime.now();
	long days =	ChronoUnit.DAYS.between(lastTime, current);
	boolean needUpdate = days > loginProperties.getNeedUpdateTerm();
	
	
	//로그인 성공
		return AuthLoginResponseVO.builder()
				.accountId(accountDto.getAccountId())//회원아이디
				.accountLevel(accountDto.getAccountLevel())//회원레벨
				.accountNickname(accountDto.getAccountNickname())//회원닉네임
				.needUpdate(needUpdate)//비밀번호 변경이 필요함
				.build();
	}
}






