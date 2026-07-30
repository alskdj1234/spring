package com.kh.spring11.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring11.configuration.StorageProperties;
import com.kh.spring11.dao.AttachDao;
import com.kh.spring11.dto.AttachDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.vo.attach.AttachInfoVO;

@Service
@Profile("local")//spring profile이 local일 때 활성화되는 서비스
public class AttachServiceLocal implements AttachService{
	@Autowired
	private AttachDao attachDao;
	@Autowired
	private StorageProperties storageProperties;
	
	//파일 업로드는 [물리적인 저장] → [정보(메타데이터) 저장]
	@Override
	public int save(MultipartFile attach) throws IllegalStateException, IOException {
		int attachNo = attachDao.sequence();
		attachDao.insert(AttachDto.builder()
					.attachNo(attachNo)
					.attachName(attach.getOriginalFilename())
					.attachType(attach.getContentType())
					.attachSize(attach.getSize())
				.build());//DB저장
		
		//File dir = new File(storageProperties.getLocal());
		File dir = storageProperties.getLocalRoot();
		dir.mkdirs();
		
		File target = new File(dir, String.valueOf(attachNo));
		attach.transferTo(target);//물리저장
		
		return attachNo;
	}
	
	@Override
	public AttachInfoVO load(int attachNo) throws IOException {
		//[1] 정보 조회
		AttachDto attachDto = attachDao.selectOne(attachNo);
		if(attachDto == null) throw new TargetNotfoundException();
		
		//[2] 실물 파일조회
		File dir = storageProperties.getLocalRoot();
		if(dir.exists() == false) throw new TargetNotfoundException();
		
		File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
		if(target.exists() == false) throw new TargetNotfoundException();
		
		//[3] 실제 파일 데이터를 불러와서 Resource 형태로 포장
		byte[] data = FileCopyUtils.copyToByteArray(target);
		Resource resource = new ByteArrayResource(data);
		
		//[4] 조회 결과를 포장해서 반환
		return AttachInfoVO.builder()
					.attachDto(attachDto)
					.resource(resource)
				.build();
	}
	
	@Override
	public void delete(int attachNo) {
		
	}
}