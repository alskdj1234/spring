package com.kh.spring09.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring09.service.EmailService;

@CrossOrigin
@RestController
@RequestMapping("/rest/cert")
public class CertRestController {
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	public void send(@RequestParam String certEmail) {
		emailService.sendCertNumber(certEmail);
	}
}




