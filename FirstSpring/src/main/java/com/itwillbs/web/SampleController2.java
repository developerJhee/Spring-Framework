package com.itwillbs.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 컨트롤러로서의 역할을 수행하게 하기위해
public class SampleController2 {
	
	// 로그출력 객체
	private static final Logger logger 
			= LoggerFactory.getLogger(SampleController2.class);
		
	// * 메서드 리턴타입이 String 일 때, 뷰테피이를 
	//	 리턴문자열.jsp 연결
	
	
	// http://localhost:8088/web/doC	
	@RequestMapping("/doC")
	// public int doC() {
	// public boolean doC() {
	// public double doC() {
		public String doC() {
		logger.info("doC실행 - int리턴");
		
		return "itwill";
	}
	
	
	// http://localhost:8088/web/doC1	
	// http://localhost:8088/web/doC1?msg=hello
	// http://localhost:8088/web/doC1?msg=hello&tel=010-1234-1234
	@RequestMapping(value = "/doC1", method = RequestMethod.GET)
	public String doC1(@ModelAttribute("msg") String m, @ModelAttribute("tel") String t) {
		// @ModelAttribute("파라미터명") String 변수 (변수 msg말고 그냥 아무 변수명 붙여도 상관없음)
		// => request.setAttribute("파라미터명" ,값)의 의미 
		
		logger.info("doC1() 메서드 호출 -> itwill.jsp 뷰 호출");
		
		
		return "itwill";
	}
}
