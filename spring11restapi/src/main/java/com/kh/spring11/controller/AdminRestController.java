package com.kh.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.dao.AdminDao;
import com.kh.spring11.service.JwtService;
import com.kh.spring11.vo.admin.AdminComplSearchRequestVO;
import com.kh.spring11.vo.admin.AdminComplSearchResponseVO;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
 @Autowired
 private AdminDao adminDao;
 @Autowired
 private JwtService jwtService;

 
 @PostMapping(value="/users", produces = "application/json")
 public List<AdminComplSearchResponseVO> complSearch(@RequestBody AdminComplSearchRequestVO request) {
	 return adminDao.complexSearch(request);
 }
}
