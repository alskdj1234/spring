package com.kh.spring09.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring09.dao.StatDao;
import com.kh.spring09.vo.ChartDataVO;
import com.kh.spring09.vo.StatVO;

@CrossOrigin
@RestController
@RequestMapping("/rest/stat")
public class StatRestController {
	@Autowired
	private StatDao statDao;
	
	@PostMapping("/country-region")
	public ChartDataVO countryByRegion() {
		List<StatVO> list = statDao.countryByRegion();
		//return list;//FE에서 쓰기 어려운 형태
		
		List<String> titles = new ArrayList<>();//제목만 들어갈 리스트
		List<Double> values = new ArrayList<>();//값만 들어갈 리스트
		
		for(StatVO statVO : list) {
			titles.add(statVO.getTitle());//제목을 추가
			values.add(statVO.getValue());//값을 추가
		}
		
		//return Map.of("titles", titles, "values", values);
		//return ChartDataVO.builder().titles(titles).values(values).build();
		return ChartDataVO.builder()
				.type("bar").titles(titles).values(values).build();
	}
	
	@PostMapping("/lecture-category")
	public ChartDataVO lectureByCategory() {
		List<StatVO> list = statDao.lectureByCategory();
		
		//분배
		List<String> titles = new ArrayList<>();
		List<Double> values = new ArrayList<>();
		
		for(StatVO statVO : list) {
			titles.add(statVO.getTitle());
			values.add(statVO.getValue());
		}
		
		return ChartDataVO.builder()
					.type("doughnut").titles(titles).values(values)
				.build();
	}
	
	@PostMapping("/lecture-type")
	public ChartDataVO lectureByType() {
		List<StatVO> list = statDao.lectureByType();
		
		//분배
		List<String> titles = new ArrayList<>();
		List<Double> values = new ArrayList<>();
		
		for(StatVO statVO : list) {
			titles.add(statVO.getTitle());
			values.add(statVO.getValue());
		}
		
		return ChartDataVO.builder()
					.type("doughnut").titles(titles).values(values)
				.build();
	}
	
	@PostMapping("/book-genre")
	public ChartDataVO bookByGenre() {
		List<StatVO> list = statDao.bookByGenre();
		
		//분배
		List<String> titles = new ArrayList<>();
		List<Double> values = new ArrayList<>();
		
		for(StatVO statVO : list) {
			titles.add(statVO.getTitle());
			values.add(statVO.getValue());
		}
		
		return ChartDataVO.builder()
					.type("bar").titles(titles).values(values)
				.build();
	}
	
	@PostMapping("/member-level")
	public ChartDataVO memberByLevel() {
		List<StatVO> list = statDao.memberByLevel();
		
		//분배
		List<String> titles = new ArrayList<>();
		List<Double> values = new ArrayList<>();
		
		for(StatVO statVO : list) {
			titles.add(statVO.getTitle());
			values.add(statVO.getValue());
		}
		
		return ChartDataVO.builder()
					.type("bar").titles(titles).values(values)
				.build();
	}
	
	@PostMapping("/board-head")
	public ChartDataVO boardByHead() {
		List<StatVO> list = statDao.boardByHead();
		
		//분배
		List<String> titles = new ArrayList<>();
		List<Double> values = new ArrayList<>();
		
		for(StatVO statVO : list) {
			titles.add(statVO.getTitle());
			values.add(statVO.getValue());
		}
		
		return ChartDataVO.builder()
					.type("doughnut").titles(titles).values(values)
				.build();
	}
}








