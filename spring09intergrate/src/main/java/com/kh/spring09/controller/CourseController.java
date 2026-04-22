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

import com.kh.spring09.dao.CourseDao;
import com.kh.spring09.dto.CourseDto;
import com.kh.spring09.exception.TargetNotfoundException;
import com.kh.spring09.service.AttachService;
import com.kh.spring09.vo.PageVO;

@Controller
@RequestMapping("/course") // 공용주소
public class CourseController {
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private AttachService attachService;
	//@RequestMapping(value="/insert", method = RequestMethod.GET)
 	@GetMapping("/insert")
	public String insert() {
 		return "course/insert";
 	}
 	
	//@RequestMapping(value="/insert", method = RequestMethod.POST)
 	@PostMapping("/insert")
 	public String insert(@ModelAttribute CourseDto courseDto,
 			//리퀘스트 파람에 밸류 적으면 수신 파라미터명과 변수명을 분리 할 수 있다.
 			@RequestParam(value="attach") List<MultipartFile> attachList) throws IllegalStateException, IOException {
 		
 		int courseNo = courseDao.sequence();
 		courseDto.setCourseNo(courseNo);
 		courseDao.insert(courseDto);
 		
 		for(MultipartFile attach : attachList) {
 			if(!attach.isEmpty()) {
 				int attachNo = attachService.save(attach);
 				courseDao.connect(courseNo,attachNo);
 			}
 		}
 		
 		return "redirect:./insertComplete";
 	}
	
	@RequestMapping("/insertComplete")
 	public String insertComplete() {
 		return"course/insertComplete";
 	}
	
	@RequestMapping("/list")
	public String list (Model model,PageVO pageVO){
		List<CourseDto> list = courseDao.selectList(pageVO);
		//페이징을 위해 추가로 전달할 값이 있다면 전달해야 한다
				int count = courseDao.count(pageVO);
				pageVO.setCount(count);//데이터 개수 설정
				model.addAttribute("pageVO", pageVO);
		model.addAttribute("list", list);
		return"course/list";
	}
	
	@RequestMapping("/detail")
	public String detail (Model model, @RequestParam int courseNo) {
		CourseDto courseDto = courseDao.selectOne(courseNo);
		if(courseDto==null)throw new TargetNotfoundException("강좌가 존재하지 않아요");
		model.addAttribute("courseDto", courseDto);
		return "course/detail";
	}
 	
	@RequestMapping("/delete")
		public String delete(@RequestParam int courseNo) {
			CourseDto courseDto = courseDao.selectOne(courseNo);
			if(courseDto == null) throw new TargetNotfoundException("존재하지 않는 국가");
			
			courseDao.delete(courseNo);
			return "redirect:./list";//상대경로
			//return "redirect:/country/list";//절대경로
		}

	//수정 매핑
		//- GET은 상세와 동일한 작업을 수행함 (보여주는 페이지가 다름)
		@GetMapping("/edit")
		public String edit(@RequestParam int courseNo, Model model) {
			CourseDto courseDto = courseDao.selectOne(courseNo);
			if(courseDto == null) throw new TargetNotfoundException("존재하지 않는 국가");
			
			model.addAttribute("courseDto", courseDto);
			return "course/edit";
		}
		
		@PostMapping("/edit")
		public String edit(@ModelAttribute CourseDto courseDto) {
			
			courseDao.update(courseDto);
			return "redirect:./detail?countryNo="+courseDto.getCourseNo();
		}

}
