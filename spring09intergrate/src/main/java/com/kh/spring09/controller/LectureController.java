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

import com.kh.spring09.dao.LectureDao;
import com.kh.spring09.dto.LectureDto;
import com.kh.spring09.exception.TargetNotfoundException;
import com.kh.spring09.service.AttachService;
import com.kh.spring09.vo.PageVO;

@Controller
@RequestMapping("/lecture")
public class LectureController {
	@Autowired
	private LectureDao lectureDao;
	@Autowired
	private AttachService attachService;
	
	@GetMapping("/insert")
	public String insert() {
		return "lecture/insert";
	}
	@PostMapping("/insert")
	public String insert(@ModelAttribute LectureDto lectureDto,
			//RequestParam에 value를 적으면 수신할 파라미터명과 변수명을 분리할 수 있다
			@RequestParam(value = "attach") List<MultipartFile> attachList) throws IllegalStateException, IOException {
		
		//강좌 정보 등록(이미지 유무와 상관없이)
		int lectureNo = lectureDao.sequence();
		lectureDto.setLectureNo(lectureNo);
		lectureDao.insert(lectureDto);
		
		//이미지를 등록 + 연결
		for(MultipartFile attach : attachList) {
			if(!attach.isEmpty()) {//이미지가 있으면
				int attachNo = attachService.save(attach);
				lectureDao.connect(lectureNo, attachNo);
			}
		}
		
		
		//return "redirect:/lecture/insertComplete";//절대
		return "redirect:./insertComplete";//상대
	}
	@RequestMapping("/insertComplete")
	public String insertComplete() {
		return "lecture/insertComplete";
	}
	
	
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute PageVO pageVO) {
		List<LectureDto> list = lectureDao.selectList(pageVO);
		model.addAttribute("list", list);
		
		int count = lectureDao.count(pageVO);
		pageVO.setCount(count);
		model.addAttribute("pageVO", pageVO);
		
		return "lecture/list";
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam int lectureNo, Model model) throws Exception {
		LectureDto lectureDto = lectureDao.selectOne(lectureNo);
		if(lectureDto == null) 
			throw new TargetNotfoundException("존재하지 않는 강좌 정보");
		model.addAttribute("lectureDto", lectureDto);
		
		//다른 시스템과 다르게 이미지가 여러개이므로 이곳에서 이미지 번호를 모두 찾아서 화면에 전달
		//→ 화면에서는 반복문으로 이미지 생성
		List<Integer> images = lectureDao.searchImage(lectureNo);
		model.addAttribute("images", images);
		
		return "lecture/detail";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam int lectureNo) {
		LectureDto lectureDto = lectureDao.selectOne(lectureNo);
		if(lectureDto == null) throw new TargetNotfoundException("존재하지 않는 강좌");
		
		try {
			List<Integer> images = lectureDao.searchImage(lectureNo);
			for(int attachNo : images) {//이미지 개수만큼 삭제를 지시
				attachService.delete(attachNo);//Attach 테이블 데이터 삭제 + 파일삭제
			}
		}
		catch(Exception e) {}
		
		lectureDao.delete(lectureNo);
		return "redirect:./list";
		//return "redirect:/lecture/list";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int lectureNo, Model model) {
		LectureDto lectureDto = lectureDao.selectOne(lectureNo);
		if(lectureDto == null) throw new TargetNotfoundException("존재하지 않는 강좌");
		
		model.addAttribute("lectureDto", lectureDto);
		
		//다른 시스템과 다르게 이미지가 여러개이므로 이곳에서 이미지 번호를 모두 찾아서 화면에 전달
		//→ 화면에서는 반복문으로 이미지 생성
		List<Integer> images = lectureDao.searchImage(lectureNo);
		model.addAttribute("images", images);
		
		return "lecture/edit";
	}
	@PostMapping("/edit")
	public String edit(@ModelAttribute LectureDto lectureDto,
				@RequestParam(value = "attach") List<MultipartFile> attachList) throws IllegalStateException, IOException {
		
		lectureDao.update(lectureDto);
		
		//첨부파일이 없으면 attachList.size() == 1인데 isEmpty() == true
		//첨부파일이 있으면 attachList.size() >= 1이고 isEmpty() == false
//		if(!attachList.get(0).isEmpty()) {
//			for(MultipartFile attach : attachList) {
//				int attachNo = attachService.save(attach);
//				lectureDao.connect(lectureDto.getLectureNo(), attachNo);
//			}
//		}
		
		for(MultipartFile attach : attachList) {
			if(!attach.isEmpty()) {
				int attachNo = attachService.save(attach);
				lectureDao.connect(lectureDto.getLectureNo(), attachNo);
			}
		}
		
		return "redirect:./detail?lectureNo="+lectureDto.getLectureNo();
	}
	
	
	//강좌 미리보기(대표 1개만)
	@RequestMapping("/image")
	public String image(@RequestParam int lectureNo) {
		List<Integer> images = lectureDao.searchImage(lectureNo);
		
		if(images.isEmpty())
			return "redirect:/images/no_image.png";
		
		return "redirect:/download/modern?attachNo="+images.get(0);
	}
	
}











