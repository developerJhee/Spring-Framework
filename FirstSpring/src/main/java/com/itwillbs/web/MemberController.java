package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;
import com.itwillbs.persistence.MemberDAOImpl;
import com.itwillbs.service.MemberService;

/**
 * 컨트롤러별 공통 URI
 * 각 주소(URI)별 호출방식 결정(GET/POST)
 * 		GET - 페이지 조회(﻿SELECT), ﻿
 * 			  정보 입력페이지(사용자에게 정보를 입력받는 페이지 : 로그인페이지)
 * 		POST - ﻿개발자가 작업을 수행하는 페이지 
 * 			   (글쓰기INSERT,글삭제DELETE,글수정UPDATE)
 * 메서드 실행결과 처리 및 이동페이지 설정
 * 예외처리
 */

@Controller
@RequestMapping("/member/*") // 이렇게 폴더를 매핑해놓으면 이 폴더 안애 있는 파일로 자동 매핑된다.
public class MemberController {	

	private static final Logger mylog = LoggerFactory.getLogger(MemberController.class);
	
	// 서비스 객체 주입
	@Inject
	private MemberService service;
	
	
	// http://localhost:8080/web/insertForm  (x)
	// http://localhost:8080/web/member/insertForm  (o) <= 이 주소로 호출해야 실행된다.
	// 톰캣 설정 후
	// http://localhost:8080/member/insertForm  (o)
	// 회원가입 (insert)
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String joinGET() throws Exception { // 나중에 생길 예외를 예방하기위해 미리 예외를 던져준다
		mylog.info("/member/insertForm -> 정보입력창(view)으로 이동");
		// 리턴타입이 void라서 이미 어디로 갈 지 정해져있음
		// void -> 호출 주소의 이름과 같은 뷰
		// String으로 변경
		return "/member/insertForm"; // String 페이지 이동 시 컨트롤러 주소 포함하기
	}
	
	
	// http://localhost:8080/member/insert  (o)
	// 회원가입 -처리(insert)
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String joinPOST(MemberVO vo) throws Exception{
		mylog.info("/member/insertPro -> DB정보 저장");
		// 한글처리(대기)
		// 전달정보 저장
		mylog.info(vo+"");
		mylog.info(vo.toString());
		
		// DB저장 -> DAO객체 생성 - 회원가입메서드 호출
//		MemberDAO dao = new MemberDAOImpl(); (x)
//		dao.insertMember(vo); (x)
		// => 서비스를 통한 DAO 호출
		service.memberJoin(vo);
		
		// 로그인 페이지 이동	
		return "redirect:/member/login";
	}
	
	// 로그인 ()
	@GetMapping(value="/login")
	public void loginGET() throws Exception{
		mylog.debug("loginGET() 호출 ");
		// 연결된 뷰페이지 구현
	}
	
	// 로그인 ()
	@PostMapping(value="/login")
	public String loginPOST(MemberVO vo, HttpSession session /* ,@RequestParam("userid") String userid*/) throws Exception{
		mylog.debug("loginPOST() 호출 ");
		
		// 전달정보 저장(userid, userpw) : 개별적인 파라미터값 대신 객체를
		mylog.debug("로그인 정보 : " + vo);
		
		// 서비스 - DAO (로그인체크)
		boolean loginStatus = service.memberLogin(vo);
		mylog.debug(" 로그인 상태 : " + loginStatus);
		
		// 로그인 여부에 따라서 페이지 이동
		// 성공 - main 페이지
		// 실패 - login 페이지
		String resultURI="";
		
		if(loginStatus) {
			// return "redirect:/member/main";
			resultURI = "redirect:/member/main";
			session.setAttribute("id", vo.getUserid()); // 서비스는 boolean타입이니까 매개변수인 vo객체에 있는 id를 가져온다.
		} else {
			// return "redirect:/member/login";
			resultURI = "redirect:/member/login";
		}
		
		return resultURI;
		// return ""; // 이렇게 하면 리턴값없는 void랑 같다
	}
	
	
	
	// http://localhost:8080/member/main
		// 메인페이지
		@RequestMapping(value = "/main",method = RequestMethod.GET)
		public String mainGET(HttpSession session) throws Exception{
			mylog.info(" mainGET() 호출 ");
			
			if(session.getAttribute("id") == null) {
				mylog.debug("아이디 정보 없음");
				return "redirect:/member/login";
			}
			
			// 연결된 뷰페이지 호출		
			return "/member/main";
		}
		
		
		
		// 로그아웃 ()
		@RequestMapping(value = "/logout",method = RequestMethod.GET)
		public String logoutGET(HttpSession session) throws Exception{
			// 로그아웃하려면 값 받아와야하니까 session정보값받는다.
			// 세션 초기화
			session.invalidate();
			
			// 페이지 이동(로그인 페이지)
			return "redirect:/member/login";
		}	
		
		
		
		// 회원 정보 조회
		@RequestMapping(value ="/info", method = RequestMethod.GET)
		public void infoGET(HttpSession session, Model model) throws Exception{
			mylog.debug("infoGET() 호출 @@@@@@@@@@@@@@@@@@@"); 
			
			// 기존 MVC로 치면 [패턴3]이다.
			// 전달정보(x) -> 세션정보(ID) 필요
			String id = (String) session.getAttribute("id");
			
			// 컨트롤러에서 바로 DAO를 불러올 수 없으니 중간의 완충제 역할을 하는 서비스 객체를 불러오자.
			// DB가서 회원정보 가져오기 -> 서비스 계층
			MemberVO vo = service.getMember(id);
			mylog.debug(vo+"");
			
			// DB정보를 뷰페이지로 전달(request)
			model.addAttribute("vo", vo);
//			model.addAttribute(service.getMember(id)); 
			// => 리턴해서 vo값을 넣지 않고도 이렇게 바로 서비스메서드 적을 수도 있다.
			
			// view 페이지 이동
			
		}
		
		
		
		// 회원정보 수정(기본의 정보를 화면에 출력) - info의 동작과 유사하기때문에 참고하기
		@RequestMapping(value ="/modify", method = RequestMethod.GET)
		public void modifyGET(HttpSession session, Model model) throws Exception{
			mylog.debug(" modifyGET() "); 
			// insertForm.jsp 참고해서 수정 뷰페이지 생성
			String id = (String) session.getAttribute("id");
			
			// 서비스  - 회원정조 조회
			// DB정보를 뷰페이지로 전달(reqeust)
			model.addAttribute("vo", service.getMember(id)); // 두 동작을 한 번에 작성완.
			
			// view 페이지 이동 => 리턴타입이 void라서 RequestMapping에 적혀있는 value값의 주소.jsp 파일로 뷰페이지 이동한다.
		}
		// 회원정보 수정(기본의 정보를 화면에 출력)
		
		
		// 회원정보 수정 (수정된 정보를 DB에 변경)
		@RequestMapping(value = "/modify", method = RequestMethod.POST)
		public String modifyPOST(MemberVO vo) throws Exception {
			mylog.debug(" modifyPOST() ");
			// 한글처리 => 필터
			// 전달정보 저장 (폼-POST : 수정정보)
			mylog.debug(vo+""); // = vo.toString()
			
			// 서비스 - DB 회원정보 수정동작
			Integer result = service.updateMember(vo);
			String uri = "";
			if(result ==1) {
				// 수정 성공(메인페이지)
				uri="redirect:/member/main";
			} else {
				// 수정 실패(수정페이지)
				uri="redirect:/member/modify";
			}
			// 페이지 이동(메인페이지)
			return uri;
		}
		
		
		
		// 회원정보 삭제 (삭제 비밀번호 입력)
		@RequestMapping(value = "/remove",method = RequestMethod.GET)
		public void removeGET() throws Exception {
			mylog.info(" removeGET() ");
			
			// 연결된 뷰페이지 이동
		}
		
		// 회원 정보 삭제(데이터 삭제)
		@RequestMapping(value = "/remove", method = RequestMethod.POST)
		public String removePOST(MemberVO vo, HttpSession session) throws Exception{
			// 전달된 파라미터값 저장
			
			// 서비스 - DAO 회원 탈퇴
			service.deleteMember(vo);
			
			// 세션정보 초기화 (로그인정보)
			session.invalidate();
			
			// 페이지 이동
			return "redirect:/member/main";
		}
		
		
		// 회원 전체 목록조회
		@RequestMapping(value ="/list", method = RequestMethod.GET)
		public String listGET(HttpSession session, Model model) throws Exception{
			// 관리자만 사용할 수 있어야하기 때문에 Session정보 들거와야한다.
			// 관리자 로그인 제어
			String id = (String) session.getAttribute("id");
			if(id == null || !id.equals("admin")) {
				return "redirect:/member/login";
			}
			
			// 서비스 - DAO 회원목록 가져오기
			List<MemberVO> memberList = service.getMemList(id);
			
			// Model 객체 사용 - 정보 전달
			// => 매개변수 자리에 Model객체 생성해야 사용할 수 있음
			model.addAttribute("memberList", memberList);
			
			// 뷰페이지 이동
			return "/member/list";
		}
		
		
	
}



