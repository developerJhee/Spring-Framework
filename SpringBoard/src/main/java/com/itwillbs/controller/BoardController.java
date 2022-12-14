package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
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
	
	// 게시판 글쓰기 GET
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
//	@GetMapping 어노테이션 바로 써도 된다.
	public void registGET() throws Exception{
		mylog.debug("/board/regist(GET) 호출 -> 페이지 이동 ");
		
		// 일반적으로 get방식은 동작을 이까지만 하면 끝난다.	
	}
	
	
	// 게시판 글쓰기 POST
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registPOST(BoardVO vo) throws Exception{
		mylog.debug("/board/regist(POST) 호출 ");
		mylog.debug("GET방식의 데이터 전달 -> DB저장 -> 페이지이동 ");
		
		// 0. 한글처리
		// 1. 전달된 정보 저장(title, content, writer)
		mylog.debug(vo+""); // mylog는 String형태만 호출가능하기때문에 연결자를 쓰던가 toString()를 같이 써준다.
		
		// 2. 서비스 -> DAO 접근(mapper)
		service.insertBoard(vo);
		mylog.debug("게시판 글쓰기 완료!");
		
		// 3. 페이지로 이동(list페이지)
		return "/board/list";
	}
	
}
