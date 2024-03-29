package com.itwillbs.web;

import javax.management.AttributeValueExp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwillbs.domain.MemberVO;

@Controller
public class SampleController3 {
	
	// 로그출력 객체
	private static final Logger logger 
	= LoggerFactory.getLogger(SampleController3.class);
	
	// 객체 -> 뷰페이지로 전달
	// /doD 호출 => itwillMember.jsp 연결 후 출력
	
	// http://localhost:8080/web/doD
	// http://localhost:8080/web/doD?userid=itwill&userpw=55555
	@RequestMapping("/doD")
	public String doD(Model model,MemberVO vo /*@ModelAttribute("userid") String userid*/) {	// 메서드명과 연결주소명이 다르기 때문에 String타입을 이용
		
		logger.info(" doD() 호출 -> itwillMember.jsp 연결 ");
		
		// 객체 정보를 전달
		// => Model 객체 (스프링에서 제공하는 컨트롤러의 정보를
		//	  뷰페이지로 전달하는 객체)
		// model.addAttribute(attributeName, attributeValue);
		
		/*MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("1234");
		vo.setUsername("관리자");
		vo.setUseremail("admin@admin.com"); 
		*/
		
		// 모델 객체에 정보를 저장(연결된 뷰페이지로 전달)
//		model.addAttribute("vo", vo);
//		model.addAttribute("cvbn", vo); // 내가 일일이 파라미터명 찾아야하는 번거로움 존재
		model.addAttribute(vo); // 위에 꺼보다 더 흔히 쓰는 코드다 
		// 파라미터명 없이 이렇게 쓸 경우 어떻게 뷰페이지에서 값을 출력할 수 있을까?
		// -> 이름이 없는 경우 전달되는 객체의 클래스명을 사용(첫글자 소문자로!!!!)	
		
//		model.addAttribute("userid", userid);
		
		return "itwillMember";
	}
	
	
//	@RequestMapping("/MemberJoin")
//	public String MemberJoinAction() {
//		
//	     return "main";
//	}
//	
//	@RequestMapping("/Main")
//	public String MainAction() {
//		
//	     return "main";
//	}
	
	
	
	
}

