package com.itwillbs.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.service.MailService;

// @EnableAsync : 비동기방시의 호출이 가능

@Controller
@EnableAsync
public class MailController {

	private static final Logger mylog = LoggerFactory.getLogger(MailController.class);
	
	// Mail서비스 객체 생성 (객체 의존주입)
	@Inject
	private MailService mailService;
	
	//	/sendMail.will 가상주소 호출 시 가상주소를 제외한 뷰페이지 연결
	@RequestMapping(value = "/sendMail.will", method = RequestMethod.GET)
	public void sendMail(HttpServletRequest request, 
						HttpServletResponse response) throws Exception{
		mylog.debug(" sendMail() 호출 "); 
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out =  response.getWriter();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html><body>");
		sb.append("<h1>안녕하세요</h1>");
		sb.append("<a href='http://www.itwillbs.co.kr'>");
		sb.append("<img src='https://media.istockphoto.com/id/1013280044/ko/%EB%B2%A1%ED%84%B0/%EB%8B%A4%EC%B1%84%EB%A1%9C%EC%9A%B4-%EA%B8%B0%ED%95%98%ED%95%99%EC%A0%81-%EC%9D%B8-%EC%8A%A4%ED%83%80%EC%9D%BC%EC%97%90-%EB%B6%80%EC%82%B0-%EC%8A%A4%EC%B9%B4%EC%9D%B4-%EB%9D%BC%EC%9D%B8-%EC%8B%A4%EB%A3%A8%EC%97%A3%EC%9E%85%EB%8B%88%EB%8B%A4-%EB%94%94%EC%9E%90%EC%9D%B8%EC%97%90-%EB%8C%80-%ED%95%9C-%EA%B8%B0%ED%98%B8%EC%9E%85%EB%8B%88%EB%8B%A4-%EB%B2%A1%ED%84%B0-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8%EC%9E%85%EB%8B%88%EB%8B%A4.jpg?s=612x612&w=0&k=20&c=My8H0tb43h0QR-CMHrlCHV2Equ-h0PUWtRB6v5ode1Q='>");
		sb.append("</a>");
		sb.append("</body></html>");
		
		String str ="";
		str += "<html><body>";
		str += "<h1>안녕하세요</h1>";
		str += "<a href='http://www.itwillbs.co.kr'>";
		str += "<img src='https://media.istockphoto.com/id/1013280044/ko/%EB%B2%A1%ED%84%B0/%EB%8B%A4%EC%B1%84%EB%A1%9C%EC%9A%B4-%EA%B8%B0%ED%95%98%ED%95%99%EC%A0%81-%EC%9D%B8-%EC%8A%A4%ED%83%80%EC%9D%BC%EC%97%90-%EB%B6%80%EC%82%B0-%EC%8A%A4%EC%B9%B4%EC%9D%B4-%EB%9D%BC%EC%9D%B8-%EC%8B%A4%EB%A3%A8%EC%97%A3%EC%9E%85%EB%8B%88%EB%8B%A4-%EB%94%94%EC%9E%90%EC%9D%B8%EC%97%90-%EB%8C%80-%ED%95%9C-%EA%B8%B0%ED%98%B8%EC%9E%85%EB%8B%88%EB%8B%A4-%EB%B2%A1%ED%84%B0-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8%EC%9E%85%EB%8B%88%EB%8B%A4.jpg?s=612x612&w=0&k=20&c=My8H0tb43h0QR-CMHrlCHV2Equ-h0PUWtRB6v5ode1Q='>";
		str += "</a>";
		str += "</body></html>";
		
		// 메일 보내기
		// 					  받는 주소					제목				내용
		//mailService.sendMail("0zhee0@gmail.com", "테스트 메일 제목", "테스트 내용 작성");
		mailService.sendMail("0zhee0@gmail.com", "테스트 메일 제목2", sb+"");

				
				
		out.print("메일 전송 성공!"); // 화면 웹에 출력
		mylog.debug("메일 전송 성공!"); // 콘솔 로그에 출력
		// return "sendMail.will"; 가상주소까지 그대로 호출
	}
}





