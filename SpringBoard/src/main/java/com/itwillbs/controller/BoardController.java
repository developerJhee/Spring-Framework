package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller // 컨트롤러로 인식하게끔 설정
@RequestMapping("/board/*") // 클래스 자체적으로 주소매핑 한번 해주기(폴더설정)
public class BoardController {
	
	private static final Logger mylog 
			= LoggerFactory.getLogger(BoardController.class);
	
	// 서비스 객체-주입
		@Inject
		private BoardService service;
	
	// http://localhost:8080/board/regist
	
	
	/**
	 * 게시판 글쓰기
	 */
	// 게시판 글쓰기 GET
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
//	@GetMapping 어노테이션 바로 써도 된다.
	public void registGET() throws Exception{
		mylog.debug("/board/regist(GET) 호출 -> 페이지 이동 ");
		
		// 일반적으로 get방식은 동작을 이까지만 하면 끝난다.	
	}
	
	
	// 게시판 글쓰기 POST
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
		mylog.debug("/board/regist(POST) 호출 ");
		mylog.debug("GET방식의 데이터 전달 -> DB저장 -> 페이지이동 ");
		
		// 0. 한글처리
		// 1. 전달된 정보 저장(title, content, writer)
		mylog.debug(vo+""); // mylog는 String형태만 호출가능하기때문에 연결자를 쓰던가 toString()를 같이 써준다.
							
		// 2. 서비스 -> DAO 접근(mapper)
		service.insertBoard(vo);
		mylog.debug("게시판 글쓰기 완료!");
		
		// 3. 페이지로 이동(list페이지)
		
//		model.addAttribute("result", "createOK");
		rttr.addFlashAttribute("result", "createOK");
		
		return "redirect:/board/list";
//		return "/list"; // 워크벤치에 글은 들어가는데 주소가 안맞음
	}
	
	
	
	/**
	 * 글목록 LIST
	 */
	// http://localhost:8080/board/list?result=createOK
	
	// http://localhost:8080/board/list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listGET(HttpSession session, Model model, @ModelAttribute("result") String result) throws Exception{
		mylog.debug(" /board/list 호출 -> DB정보 가져와서 출력 ");
		
		// 전달받은 정보X -> 생겨버렸네
		mylog.debug(" 전달정보 : " + result);
		
		// 세션객체 - 글 조회수 증가 체크정보
		session.setAttribute("updateCheck", true); // 세션이 존재하면 true
		
		// 서비스 -> DAO 게시판 리스트 가져오기
		List<BoardVO> boardList = service.getBoardListAll();
		
		// 연결되어 있는 뷰페이지로 정보 전달 (Model 객체)
		model.addAttribute("boardList", boardList);
		
		// 페이지 이동(/board/list.jsp)
		// => 메서드의 리턴타입이 void이기 때문에 매핑된 name의 뷰페이지를 알아서 찾아준다.
	}
	
	
	/**
	 * 게시판 본문보기
	 */	
	// @ModelAttribute : request.getParameter() 동작 ->  Model 객체에 저장 (1:n 관계)
	// @RequestParam : request.getParameter() 동작 (1:1관계) 
	//				   => 문자열, 숫자, 날짜 등 데이터 형변환 가능
	
	// http://localhost:8080/board/read?bno=?1
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	// public void readGET(@ModelAttribute("bno") int bno) throws Exception{
	public void readGET(Model model, @RequestParam("bno") int bno, HttpSession session) throws Exception{
		// 전달정보 (bno)
		mylog.debug("전달정보(bno) : "+bno);
		
		// 세션객체 (for 조회수)
		boolean isUpdateCheck = (boolean) session.getAttribute("updateCheck");
		mylog.debug("조회수 증가 상태 : " + isUpdateCheck);
		
		// 조회수 1증가(list -> read 증가O, F5 증가X)
				// 세션객체를 사용해서 list -> read일때만 사용가능하도록(=A)
				// 조회수 증가 후 = B (그러므로 B에서는 동작X)
				// 리스트로 돌아가면 다시 A
		if(isUpdateCheck) { //= true
			// 조회수 1증가(list -> read 증가o, F5증가X)
			service.updateViewcnt(bno);
			mylog.debug("조회수 1증가" );
			
			// 상태변경 (true -> false);
			session.setAttribute("updateCheck", !isUpdateCheck);				
		}
		
		// 서비스 -> DAO (특정 글번호에 해당하는 정보 가져오기)
		BoardVO vo =service.getBoard(bno);
		
		// 연결된 뷰페이지로 정보 전달(model 객체 써야함)		
		model.addAttribute("vo", vo);
		
//	=	model.addAttribute("vo", service.getBoard(bno)); 이렇게 한 번에 쓸 수도 있다.
//	=	model.addAttribute(service.getBoard(bno));
		
	}
	
	
	/**
	 * 수정하기 GET
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(Model model, int bno) throws Exception{
							// modelAttribute를 쓰든 requestParam을 쓰든 상관없다.
							// 특정 기본형 데이터값 하나만 전달할 경우 자동변환이 되므로 int bno만 적어도 가능
		// 전달정보 bno 파라미터 저장
		// 서비스 - DAO(글 조회)				
		// model 객체 사용해서 원하는 페이지로 이동
		// -> view 페이지로 정보 전달
		model.addAttribute("vo", service.getBoard(bno));
		
		// /board/modify.jsp 페이지 이동 => void라서 modify로 자동이동
	}
	
	
	
	/**
	 * 수정하기 POST
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
		// 전달된 정보(수정할정보)저장
		mylog.debug(vo+"");
		
		// 서비스 - DAO : 정보 수정 메서드 호출 (RedirectAttributes 객체 생성)
		Integer result = service.updateBoard(vo);
		
		if(result > 0) {
			// "수정완료" - 정보 전달
			rttr.addFlashAttribute("result", "modOK");
		}
		
		// 페이지이동(/board/list)
		
		return "redirect:/board/list";
	}
	
	
	
	/**
	 * 글 삭제하기 POST
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePOST(int bno, RedirectAttributes rttr) throws Exception{
		// 전달정보 저장(bno) => form -submit하니가 전달정보 존재
		mylog.debug(bno+"");
		// 서비스-DAO : 게시판글 삭제 메서드 호출
		service.deleteBoard(bno);
		// "삭제완료" 정보를 list 페이지로 전달
		rttr.addFlashAttribute("result", "delOK");
		// 게시판 리스트로 이동(/board/list)
		
		return "redirect:/board/list";
	}
	
	
	/**
	 * 페이징처리된 글 목록 
	 */
	// http://localhost:8080/board/listPage
	// http://localhost:8080/board/listPage?page=2
	// http://localhost:8080/board/listPage?perPageNum=30
	// http://localhost:8080/board/listPage?page=3&perPageNum=20
	@RequestMapping(value = "/listPage",method = RequestMethod.GET)
	public String listPageGET(Criteria cri,HttpSession session,Model model) throws Exception {
		mylog.debug(" /board/listPage 호출 -> DB정보 가져와서 출력 ");
		
		// 전달받은 정보X -> 생겨버렸네
//		mylog.debug(" 전달정보 : " + result);
		
		// 세션객체 - 글 조회수 증가 체크정보
		session.setAttribute("updateCheck", true); // 세션이 존재하면 true
		
		// 페이징처리객체 (강한결합 -> X)
//		Criteria cri = new Criteria();
		
		// 서비스 -> DAO 게시판 리스트 가져오기
		List<BoardVO> boardList = service.getListPage(cri);
		
		// 페이징처리 하단부 정보 준비
		PageVO pvo = new PageVO();
		pvo.setCri(cri);
		pvo.setTotalCount(7964);
		
		model.addAttribute("pvo", pvo);
		
		// 연결되어 있는 뷰페이지로 정보 전달 (Model 객체)
		model.addAttribute("boardList", boardList);
		
		// 페이지 이동(/board/list.jsp)	
		return "/board/list";
	}
	
	
}
