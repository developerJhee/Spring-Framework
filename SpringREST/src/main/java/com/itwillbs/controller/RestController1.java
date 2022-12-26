package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController1 {
	
	private static final Logger mylog = LoggerFactory.getLogger(RestController1.class);
	
	@RequestMapping("/test1")
	public String test1() throws Exception{
		mylog.debug("test1() 호출");
		
		return "hello";
	}
	
	@RequestMapping("/test2")
	public @ResponseBody String test2() throws Exception{
		// => 데이터 자체를 리턴
		mylog.debug("test2() 호출");
		
		return "hello2";
	}
	
	// http://localhost:8080/rest1
	@RequestMapping(value = "/rest1")
	public String restTest1() throws Exception{
		mylog.debug(" /rest1 -> 연결된 뷰jsonTest1.jsp -> 데이터 생성 -> RestController ");
		
		return "jsonTest1";
	}
}
