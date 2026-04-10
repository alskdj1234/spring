package com.kh.spring09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring09.dao.CountryDao;
import com.kh.spring09.dto.CountryDto;
import com.kh.spring09.exception.TargetNotfoundException;

@Controller
@RequestMapping("/country")
public class CountryController {
 @Autowired
 	private CountryDao countryDao;
 
 	//등록(화면과 처리 코드 결합)
 	// 입력 -> 처리 + 출력
 	@GetMapping("/insert")
 	public String insert() {
 		return "/WEB-INF/views/country/insert.jsp";
 	}
 	
 	@PostMapping("/insert")
 	public String insert(@ModelAttribute CountryDto countryDto) {
 		
 		countryDao.insert(countryDto);
 		return "redirect:./insertComplete";
 	}
 	
 	@RequestMapping("/insertComplete")
 	public String insertComplete() {
 		return"/WEB-INF/views/country/insertComplete.jsp";
 	}
 	
 	@RequestMapping("/list")
 	public String list(Model model,
 			@RequestParam(required=false) String column, @RequestParam(required=false) String keyword ) {
 		List<CountryDto> list = countryDao.selectList(column,keyword);
 		
 		model.addAttribute("list", list);
 		
 		return"/WEB-INF/views/country/list.jsp";
 		
 	}
 	
 	@RequestMapping("/detail")
 	public String detail(Model model,@RequestParam int countryNo) {
 		CountryDto countryDto=countryDao.selectOne(countryNo);
 		//잘못된 번호인 경우 (countryDto==null)이를 오류(500)으로 처리
 		if(countryDto==null) {
 			throw new TargetNotfoundException("국가가 없어요");
 		}
 		model.addAttribute("countryDto", countryDto);
 	 return"/WEB-INF/views/country/detail.jsp";
 	}
 	
 	//삭제 매핑
 		@RequestMapping("/delete")
 		public String delete(@RequestParam int countryNo) {
 			CountryDto countryDto = countryDao.selectOne(countryNo);
 			if(countryDto == null) throw new TargetNotfoundException("존재하지 않는 국가");
 			
 			countryDao.delete(countryNo);
 			return "redirect:./list";//상대경로
 			//return "redirect:/country/list";//절대경로
 		}
 		
 		//수정 매핑
 		//- GET은 상세와 동일한 작업을 수행함 (보여주는 페이지가 다름)
 		@GetMapping("/edit")
 		public String edit(@RequestParam int countryNo, Model model) {
 			CountryDto countryDto = countryDao.selectOne(countryNo);
 			if(countryDto == null) throw new TargetNotfoundException("존재하지 않는 국가");
 			
 			model.addAttribute("countryDto", countryDto);
 			return "/WEB-INF/views/country/edit.jsp";
 		}
 		
 		@PostMapping("/edit")
 		public String edit(@ModelAttribute CountryDto countryDto) {
 			
 			countryDao.update(countryDto);
 			return "redirect:./detail?countryNo="+countryDto.getCountryNo();
 		}
 	
}
