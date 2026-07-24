package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.vo.admin.AdminComplSearchRequestVO;
import com.kh.spring11.vo.admin.AdminComplSearchResponseVO;

public interface AdminDao {
	List<AdminComplSearchResponseVO> complexSearch(AdminComplSearchRequestVO vo);
}
