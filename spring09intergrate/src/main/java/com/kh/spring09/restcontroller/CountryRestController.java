package com.kh.spring09.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring09.dao.CountryLikeDao;
import com.kh.spring09.vo.LikeVO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/country")
public class CountryRestController {
	@Autowired
	private CountryLikeDao countryLikeDao;
	
	@PostMapping("/like-check")
	public LikeVO likeCheck(@RequestParam int countryNo, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		boolean action = countryLikeDao.check(loginId,countryNo);
		
		int count = countryLikeDao.count(countryNo);
		
		return LikeVO.builder().action(action).count(count).build();
		
	}
	
	@PostMapping("/like-action")
	public LikeVO likeAction(@RequestParam int countryNo, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		boolean current = countryLikeDao.check(loginId, countryNo);
		
		if(current) {
			countryLikeDao.delete(loginId, countryNo);
		}
		
		else {
			countryLikeDao.insert(loginId, countryNo);
		}
		int count = countryLikeDao.count(countryNo);
		
		return LikeVO.builder().action(!current).count(count).build();
	}
}
