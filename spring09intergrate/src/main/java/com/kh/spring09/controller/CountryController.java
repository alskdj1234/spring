package com.kh.spring09.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring09.dao.CountryDao;
import com.kh.spring09.dto.CountryDto;
import com.kh.spring09.exception.TargetNotfoundException;
import com.kh.spring09.service.AttachService;
import com.kh.spring09.vo.PageVO;

@Controller
@RequestMapping("/country")
public class CountryController {
	@Autowired
	private CountryDao countryDao;
	@Autowired
	private AttachService attachService;
	
	//등록(화면과 처리 코드 결합)
	//- 예상되는 흐름 : [입력] → [처리+출력]
	//@RequestMapping(value = "/insert", method = RequestMethod.GET)
	@GetMapping("/insert")
	public String insert() {
		return "country/insert";
	}
	//@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@PostMapping("/insert")
	public String insert(@ModelAttribute CountryDto countryDto,
						@RequestParam MultipartFile attach) throws IllegalStateException, IOException {
		//번호 생성 후 국가 등록하도록 처리
		int countryNo = countryDao.sequence();
		countryDto.setCountryNo(countryNo);
		countryDao.insert(countryDto);
		
		if(!attach.isEmpty()) {//국기가 있을 경우
			int attachNo = attachService.save(attach);//등록 처리
			countryDao.connect(countryNo, attachNo);//국가번호와 파일번호를 연결
		}
		
		//return "redirect:/country/insertComplete";//절대경로
		return "redirect:./insertComplete";//상대경로
	}
	@RequestMapping("/insertComplete")
	public String insertComplete() {
		return "country/insertComplete";
	}
	
	//목록 및 검색에 대한 매핑
	@RequestMapping("/list")
	public String list(@ModelAttribute PageVO pageVO, Model model) {
		//목록 조회
		List<CountryDto> list = countryDao.selectList(pageVO);
		
		//모델에 첨부
		model.addAttribute("list", list);
		
		int count = countryDao.count(pageVO);
		pageVO.setCount(count);
		model.addAttribute("pageVO", pageVO);
		
		return "country/list";
	}
	
	//상세조회 매핑
	@RequestMapping("/detail")
	public String detail(@RequestParam int countryNo, Model model) {
		CountryDto countryDto = countryDao.selectOne(countryNo);
		//잘못된 번호인 경우(countryDto==null) 이를 오류(500)로 처리
		if(countryDto == null) {
			throw new TargetNotfoundException("존재하지 않는 국가");
		}
		model.addAttribute("countryDto", countryDto);
		
		//국기에 대한 파일번호를 찾아서 첨부한다면..?
		try {
			int attachNo = countryDao.searchFlag(countryNo);
			model.addAttribute("attachNo", attachNo);
		}
		catch(Exception e) {}
		
		return "country/detail";
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
		return "country/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute CountryDto countryDto) {
		countryDao.update(countryDto);
		return "redirect:./detail?countryNo="+countryDto.getCountryNo();
	}
	
}










