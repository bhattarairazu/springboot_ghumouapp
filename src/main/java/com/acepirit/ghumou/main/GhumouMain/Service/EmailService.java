package com.acepirit.ghumou.main.GhumouMain.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMainSender;
	
	//for sending email asynchronosly
	@Async
	public void sendEmail(SimpleMailMessage email) {
		javaMainSender.send(email);
	}

}
