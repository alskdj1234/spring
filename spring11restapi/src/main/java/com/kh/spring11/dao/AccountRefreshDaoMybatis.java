package com.kh.spring11.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.AccountRefreshDto;
@Repository

public class AccountRefreshDaoMybatis implements AccountRefreshDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertOrUpdate(AccountRefreshDto accountRefreshDto) {
	AccountRefreshDto findDto =	sqlSession.selectOne("mapper.accountRefresh.find",accountRefreshDto);
		if(findDto == null) {//없으니 인서트
			sqlSession.insert("mapper.accountRefresh.add",accountRefreshDto);
		}
		else {//있으니 업뎃
			sqlSession.update("mapper.accountRefresh.change", accountRefreshDto);
		}
		
	}

	@Override
	public void delete(AccountRefreshDto accountRefreshDto) {
		sqlSession.delete("mapper.accountRefresh.delete",  accountRefreshDto );
		

	}

	@Override
	public AccountRefreshDto find(AccountRefreshDto accountRefreshDto) {
		
		return sqlSession.selectOne("mapper.accountRefresh.find", accountRefreshDto);
	}

}
