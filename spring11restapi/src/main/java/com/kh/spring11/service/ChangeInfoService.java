package com.kh.spring11.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.kh.spring11.dao.AccountChangePasswordDao;
import com.kh.spring11.vo.account.AccountChangePasswordVO;
import com.kh.spring11.vo.cert.CertCheckRequestVO;

import jakarta.mail.MessagingException;

@Service
public class ChangeInfoService {
	@Autowired
	private AccountChangePasswordDao accountChangePasswordDao;
	
	@Autowired
	private CertService certService;
	
	@Autowired
	private EmailService emailService;

	public String changePassword(@RequestBody AccountChangePasswordVO vo) throws MessagingException, IOException
			{
		emailService.sendCertNumber2(vo.getAccountEmail());
		
	CertCheckRequestVO certVO=	CertCheckRequestVO.builder()
		.certEmail(vo.getAccountEmail())
		.certNumber(vo.getCertNumber())
		.build();
		
	certService.check(certVO);
		

		
		return vo.getAccountPassword();
	}
}
