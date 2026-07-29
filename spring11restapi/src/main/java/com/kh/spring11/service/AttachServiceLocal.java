package com.kh.spring11.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring11.dao.AttachDao;
import com.kh.spring11.dto.AttachDto;

@Service
@Profile("local")
public class AttachServiceLocal implements AttachService {
	@Autowired
	private AttachDao attachDao;
	
	//파일 업로드는 물리적 저장 -> 정보(메타 데이터) 저장
	@Override
	public int save(MultipartFile attach) throws IllegalStateException, IOException {
		int attachNo = attachDao.sequence();
		
		attachDao.insert(AttachDto.builder()
				.attachNo(attachNo)
				.attachName(attach.getOriginalFilename())
				.attachType(attach.getContentType())
				.attachSize(attach.getSize())
				.build());//db 저장
		File dir = new File("D:/upload");
		File target = new File(dir, String.valueOf(attachNo));
		attach.transferTo(target);//물리 저장
		
		return attachNo;
	}

	@Override
	public void delete(int attachNo) {
		// TODO Auto-generated method stub
		
	}

}
