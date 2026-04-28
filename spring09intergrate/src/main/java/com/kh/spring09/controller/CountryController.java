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
		
<<<<<<< HEAD
		return "country/detail";
	}
	
	//삭제 매핑
	@RequestMapping("/delete")
	public String delete(@RequestParam int countryNo) {
		CountryDto countryDto = countryDao.selectOne(countryNo);
		if(countryDto == null) throw new TargetNotfoundException("존재하지 않는 국가");
		
		//국가정보가 지워지면 국기 데이터도 지워지는데 DB만 지워지고 파일은 그대로 남아있다는 문제 발생
		//파일(attach) 정보와 실물 파일을 지울 수 있도록 국가 정보 삭제 전에 파일 번호를 알아내야 한다
		//→ 만약 파일이 없어서 예외가 발생한다면? 그냥 국가 정보만 삭제~
		try {
			int attachNo = countryDao.searchFlag(countryNo);//국기 찾으세요~
			attachService.delete(attachNo);//Attach 테이블 데이터 삭제 + 파일삭제
		}
		catch(Exception e) {}
		
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
	public String edit(@ModelAttribute CountryDto countryDto, 
						@RequestParam MultipartFile attach) throws IllegalStateException, IOException {
		
		countryDao.update(countryDto);
		
		//첨부파일이 있다면 기존것을 제거하고 신규 등록
		if(!attach.isEmpty()) {
			try {
				int attachNo = countryDao.searchFlag(countryDto.getCountryNo());//원래 깃발 번호 가져와
				attachService.delete(attachNo);//지워
			} catch(Exception e) {/*없어? 기존 깃발이 없나보네*/}
			
			int attachNo = attachService.save(attach);//새로 저장해!
			countryDao.connect(countryDto.getCountryNo(), attachNo);//연결도 시켜놔
		} 
		
		return "redirect:./detail?countryNo="+countryDto.getCountryNo();
	}
	
	//국기를 반환하는 매핑
	@RequestMapping("/flag")
	public String flag(@RequestParam int countryNo) {
		try {
			//Plan A - 이미지가 존재하는 경우
			int attachNo = countryDao.searchFlag(countryNo);
			return "redirect:/download/modern?attachNo="+attachNo;
		}
		catch(Exception e) {
			//Plan B - 이미지가 존재하지 않는 경우
			return "redirect:/images/no_image.png";
		}
=======
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
>>>>>>> branch 'main' of https://github.com/alskdj1234/spring.git
	}
	
}










<<<<<<< HEAD

=======
>>>>>>> branch 'main' of https://github.com/alskdj1234/spring.git
