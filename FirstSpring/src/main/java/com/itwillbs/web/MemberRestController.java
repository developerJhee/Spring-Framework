package com.itwillbs.web;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberRestController {

	private static final Logger mylog = LoggerFactory.getLogger(MemberRestController.class);
	
	// 디비 접근을 위한 서비스 객체 주입
	@Inject
	private MemberService service;
	
	// GET 방식 - members/ckID + 데이터
	// 아이디 정보를 전달받아서 서비스에서 해당아이디가 중복인지 아닌지 여부판단 => 데이터조회
	@RequestMapping(value = "/ckID", method = RequestMethod.GET)
	public String checkID(MemberVO vo, 
				@ModelAttribute("userid") String userid) throws Exception{
		mylog.debug("checkID() 호출");		
		mylog.debug(vo+"");		
		mylog.debug(userid+"");		
		
		//service.getMember(vo.getUserid());
		MemberVO checkVO = service.getMember(userid);
		mylog.debug(checkVO+"");
		
		if(checkVO == null) {
			// 디비에 정보가 없음 -> 해당 아이디 사용가능
			return "OK";
		}else {
			// 디비에 정보가 있음 -> 해당 아이디 사용불가능
			return "NO";
		}
	}
}
