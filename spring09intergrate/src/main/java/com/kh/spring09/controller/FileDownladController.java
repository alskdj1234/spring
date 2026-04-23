package com.kh.spring09.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring09.dao.AttachDao;
import com.kh.spring09.dto.AttachDto;
import com.kh.spring09.exception.TargetNotfoundException;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/download")
public class FileDownladController {
	@Autowired
	private AttachDao attachDao;
	
	//자바 기본
	@RequestMapping("/legacy")
	public void legacy(@RequestParam int attachNo, HttpServletResponse response) throws IOException {
			//전달된 attachNo에 해당하는 정보를 불러와 파일과 조합해 사용자에게 전술
			
			//  정보 조회
			AttachDto attachDto = attachDao.selectOne(attachNo);
			if(attachDto == null) throw new TargetNotfoundException("존재 하지 않는 파일");
			
			// 파일 조회
			File dir = new File("C:/uplo;ad");
			File target = new File(dir, String.valueOf(attachNo));
			if(!target.isFile()) throw new TargetNotfoundException("존재하지 않는 파일");
			
			//사용자에게 알려줄 다운로드 정보(헤더) 설정
			response.setHeader("Content-Encoding", "UTF-8");
			response.setHeader("Content-Type", attachDto.getAttachTypeString());
			response.setHeader("Content-Disposition", "attachment; filename="+attachDto.getAttachName());
			response.setHeader("Content-Length", String.valueOf(attachDto.getAttachSize()));
			FileInputStream stream = new FileInputStream(target);
			byte[] buffer = new byte[1024];
			while(true) {
				int size = stream.read(buffer);
				if(size==-1) break;
				response.getOutputStream().write(buffer,0,size);
			}
			stream.close();
	}
}
