package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
	
		
	// 로그출력 객체
	private static final Logger logger 
	= LoggerFactory.getLogger(SampleController4.class);
	
	// 동작 처리 이후에 페이지 이동(주소이동)
	
	// http://localhost:8080/web/doE
	// http://localhost:8080/web/doE?msg=hello
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr /*Model model*/) {
		logger.info("EEEEEEEEEEEEEE");
		
//		rttr.addAttribute("msg", "ITWILL"); 
		// 기존의 Model 객체와 동일
		// - URI에 표시됨, F5(새로고침) 시 데이터 유지
		// 기존의 이동방식(리다이렉트,뷰이동) 다 가능
		
		rttr.addFlashAttribute("msg", "ITWILL"); 
		// flash : 일회성(휘발성), ﻿전달정보를 일회성으로만 사용할 수 있게 해준다.
		// - URI에 표시가 안 됨, F5(새로고침) 시 데이터 사라짐=임시데이터
		// 	 ex) 본인인증용, 본인확인용
		// only 리다이렉트 이동 시에만 사용 가능
		
//		model.addAttribute("msg", "ITWILL");
		// => 주소줄에 파라미터 형태로 전달 /doF?msg=hello
		
//		return "/doF"; // 이 방식은 리다이렉트가 아니라 뷰페이지 호출만 된다.
//		return "forward:/doF"; //=> 포워딩방식
//		return "redirect:/doF?msg=hello"; //=> 리다이렉트 방식
		return "redirect:/doF"; //=> 리다이렉트 방식
	}
	
	// http://localhost:8080/web/doF
	@RequestMapping("/doF")
	public String doF(@ModelAttribute("msg") String msg) {
		logger.info("FFFFFFFFFFFFFFFFFFFFFF");
		
		// 뷰 따로 안 만들고 여기서 바로 출력해보자.
		logger.info("msg : " + msg);
		return "doF실행";
	}
	
	
	
}
