package com.kh.spring11.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import com.kh.spring11.vo.account.AccountChangePasswordVO;
@Repository
public class AccountChangePasswordDaoMybatis implements AccountChangePasswordDao {
 @Autowired
 private SqlSession sqlSession;
 @Autowired
 private PasswordEncoder passwordEncoder;

 @Override
	public AccountChangePasswordVO selectOne(String accountId) {
	
		
		return sqlSession.selectOne("mapper.accountChangeInfo.selectOne",accountId );
	}

	@Override
	@Transactional // 👈 예외(Exception) 터지면 자동으로 DB 작업 롤백시킴
	public boolean changePassword(AccountChangePasswordVO accountChangePasswordVO) {

	    // 1. 입력받은 평문 비밀번호 추출
	    String origin = accountChangePasswordVO.getAccountPassword();
	    
	    // 2. 평문이 비어있거나 이상하면 예외 던져서 롤백 시도조차 안 하게 막음
	    if (origin == null || origin.trim().isEmpty()) {
	        throw new RuntimeException("비밀번호가 유효하지 않아서 롤백합니다.");
	    }

	    // 3. 비밀번호 암호화 후 VO에 다시 세팅
	    String originEncrypt = passwordEncoder.encode(origin);
	    accountChangePasswordVO.setAccountPassword(originEncrypt);

	    // 4. 암호화된 비밀번호가 정상적으로 만들어졌는지 확인 (혹시나 null/빈값 일 때 대비)
	    if (originEncrypt == null || originEncrypt.equals(origin)) {
	        throw new RuntimeException("비밀번호 암호화 실패로 인한 롤백"); // 👈 예외를 던져야 @Transactional이 롤백함!
	    }

	    // 5. 암호화된 데이터로 DB 업데이트 실행
	    int checker = sqlSession.update("mapper.accountChangeInfo.changePassword", accountChangePasswordVO);

	    // 6. DB 업데이트 행(row) 수가 0개면(수정 실패시) 예외 던져서 롤백
	    if (checker <= 0) {
	        throw new RuntimeException("DB 비밀번호 변경 처리 실패로 인한 롤백");
	    }

	    return checker>0;
	}

}
