package com.kh.spring08.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring08.service.AttachService;

@Controller
@RequestMapping("/filetest")
public class FileTestController {
	@Autowired
	private AttachService attachService;
	@GetMapping("/uploadTest")
	public String uploadTest() {
		return"/WEB-INF/views/filetest/uploadTest.jsp";
	}
	//첨부파일 받을려면 MultipartFile 써야 함
	@PostMapping("/uploadTest")
	public String uploadTest(@RequestParam String uploader, //리콰이어드 여부 상관 없이 파일은 선택하지 않아도 멀티 파트 객체가 생긴다.
			@RequestParam MultipartFile attach) throws IOException {
		System.out.println("uploader = "+uploader);
		System.out.println("이름 = "+attach.getOriginalFilename());
		System.out.println("유형 = "+attach.getContentType());
		System.out.println("크기 = "+attach.getSize());
		//byte[] data = attach.getBytes();//데이터 모두 추출
		//System.out.println("데이타: "+Arrays.toString(data));
		
		if(!attach.isEmpty())
		attachService.save(attach);
		return"redirect:./uploadTest";
	}
}
