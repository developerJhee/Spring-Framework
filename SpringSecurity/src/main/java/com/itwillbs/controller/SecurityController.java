package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sec")
public class SecurityController {

	private static final Logger mylog = LoggerFactory.getLogger(SecurityController.class);
	
	//http://localhost:8080/sec/all
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public void doALL() throws Exception{
		mylog.debug(" doALL() 실행 - 누구나 사용할 수 있는 페이지");
	}
	
	//http://localhost:8080/sec/member
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public void doMember() throws Exception{
		mylog.debug(" doMember() 실행 - 회원만 사용가능 페이지");
	}
	
	//http://localhost:8080/sec/admin
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public void doAdmin() throws Exception{
		mylog.debug(" doAdmin() 실행 - 관리자만 사용가능 페이지");
	}
	
}
