package com.kh.spring09.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring09.dao.StatDao;
import com.kh.spring09.vo.StatVO;

@CrossOrigin
@RestController
@RequestMapping("/rest/stat")
public class StatRestController {
	@Autowired
	private StatDao statDao;
	
	@PostMapping("/country-region")
	public Map<String, Object> countrByRegion(){
		List<StatVO>list = statDao.countryByRegion();
		//return list;//fe에서 쓰기 어려움
		
		List<String> titles = new ArrayList<>();//제목만 들어갈 리스트
		List<Double> values = new ArrayList<>();//값만 들어갈 리스트
		
		for(StatVO statVO : list) {
			titles.add(statVO.getTitle());
			values.add(statVO.getValue());
			
		}
		
		return Map.of("titles", titles, "values", values);
	}
}
