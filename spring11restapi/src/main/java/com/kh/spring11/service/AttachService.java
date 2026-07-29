package com.kh.spring11.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface AttachService {
	int save(MultipartFile attach) throws IllegalStateException, IOException;
	void delete(int attachNo);
	
}
