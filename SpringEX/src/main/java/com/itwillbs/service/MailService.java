package com.itwillbs.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {

	private static final Logger mylog = LoggerFactory.getLogger(MailService.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	// @Async : 비동기방식으로 처리하는 메서드
	@Async
	public void sendMail(String to, String subject, String body) throws Exception{
		// 제목과 내용의 형태들을 받아와서 실행해 볼 것이다.
		mylog.debug(" to : " + to);
		mylog.debug(" subject : " + subject);
		mylog.debug(" body : " + body);
		
		MimeMessage message = mailSender.createMimeMessage();
		// -> 메일을 전송하기위한 MimeMessage 객체 생성
		
		MimeMessageHelper messageHelper 
		                = new MimeMessageHelper(message,true,"UTF-8");
		
		messageHelper.setFrom("itwillTest0909@gmail.com","아이티윌부산"); // 이메일주소(보내는사람),사람이름
		messageHelper.setTo(to); // 이메일주소(받는사람) => 우리는 일단 한 명만 가능하도록
		messageHelper.setSubject(subject); // 이메일 제목
		//messageHelper.setText(body); // 이메일 본문
		messageHelper.setText(body,true); // 이메일 본문,html사용
		
		// 메일전송
		mailSender.send(message);
		mylog.debug("메일 전송완료!!!!!!");
	}
}
