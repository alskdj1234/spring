package com.kh.spring11.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring11.vo.admin.AdminComplSearchRequestVO;
import com.kh.spring11.vo.admin.AdminComplSearchResponseVO;
@Repository
public class AdminDaoMybatis implements AdminDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<AdminComplSearchResponseVO> complexSearch(AdminComplSearchRequestVO vo) {

		return sqlSession.selectList("mapper.adminSearch.complexSearch", vo);
	}
}
