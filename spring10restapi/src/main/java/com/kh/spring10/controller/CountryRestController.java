package com.kh.spring10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring10.dao.CountryDao;
import com.kh.spring10.dto.CountryDto;
import com.kh.spring10.vo.ListVO;
@CrossOrigin
@RestController
@RequestMapping("/api/country")
public class CountryRestController{
	@Autowired
	private CountryDao countryDao;

		@GetMapping("/list")
		public List<CountryDto> list(){
			return countryDao.selectList(1, 10000);
		}

		@GetMapping("/listForReact")
		public ListVO listForReact(@RequestParam(defaultValue="0", required=false) int lastCountryNo, 
				@RequestParam(defaultValue="10", required = false) int size)
		{
			List list = countryDao.selectListForReact(lastCountryNo, size);
			int count = countryDao. countForReact(lastCountryNo);
			return ListVO.builder()
						.list(list)
						.last(count <= size)//보기로 한 개수보다 실제 데이터 수가 같거나 적으면 마지막
						.build();
		}
		
		@PostMapping("/insert")
		public void insert(@RequestBody CountryDto countryDto) {
			int countryNo = countryDao.sequence();
			countryDto.setCountryNo(countryNo);
			countryDao.insert(countryDto);
		}
		
		@GetMapping("/detail")
		public CountryDto detail(@RequestParam int countryNo) {
			return countryDao.selectOne(countryNo);
		}
		
		@PostMapping("/delete")
		public void delete(@RequestParam int countryNo) {
			countryDao.delete(countryNo);
		}
		
		@PostMapping("/edit")
		public void edit(@RequestBody CountryDto countryDto) {
			
			countryDao.update(countryDto);
		}

		
}
