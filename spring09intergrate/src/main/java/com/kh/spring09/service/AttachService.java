package com.kh.spring09.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring09.dao.AttachDao;
import com.kh.spring09.dto.AttachDto;

//서비스 : 하나의 단위 작업을 구현하기 위한 도구
@Service
public class AttachService {
	@Autowired
	private AttachDao attachDao;
	
	//서비스는 메소드의 정해진 형태과 없다.
	
	public  int save( MultipartFile attach ) throws IllegalStateException, IOException {
		int attachNo = attachDao.sequence();//파일 번호 생성
		AttachDto attachDto = new AttachDto();//db 저장용 객체
		attachDto.setAttachNo(attachNo);//번호 설정
		attachDto.setAttachName(attach.getOriginalFilename());//업로드 된 파일명 설정
		attachDto.setAttachType(attach.getContentType());//파일 유형 설정
		attachDto.setAttachSize(attach.getSize());
		
		attachDao.insert(attachDto);
		
		
		File dir = new File("D:/upload");
		dir.mkdirs();
		File target = new File(dir, String.valueOf(attachNo));
		attach.transferTo(target);
		return attachNo;
	}
}
