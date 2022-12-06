package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// @Controller : 해당 클래스가 컨트롤러역할 수행

@Controller
@RequestMapping("/test/*")
public class SampleController1 {

	// 로그출력 객체
	private static final Logger logger 
			= LoggerFactory.getLogger(SampleController1.class); // 컨트롤러 필수조건은 아니나 있어야한다.
	
	
	
	// * 메서드의 리턴타입이 void 경우 "호출주소.jsp"로 뷰 연결
	

	// http://localhost:8080/[프로젝트이름]
	// http://localhost:8080/[top-level패키지에 가장 마지막으로 적혀있는 이름 => web]/doA
	// 즉, http://localhost:8080/web/doA
	// test매핑후에는, http://localhost:8080/web/test/doA  이렇게 주소가 바뀌어야한다.
	// => 웹에서는 오류 뜨는 게 정상이고 콘솔에서만 값이 잘 나오면 된다.
	// @RequestMapping(value = "/Login.me") : Login.me 일 때 동작을 실행해라는 의미
	@RequestMapping(value = "/doA") // 가상주소 doA 와 전달방식에 반응할 수 있게 어노테이션 매핑해줘야한다.
	public void doA() {
		logger.info("/doA 호출 -> doA() 실행");
	}
	
	
	// /doB 주소 호출되는 doB()메서드
	// 즉, http://localhost:8080/web/doB
	// @RequestMapping(value = "/doB")
//	@RequestMapping(value = "/doB", method = RequestMethod.GET) // doB가 GET방식으로 전달됐을 때 doB()메서드를 실행해라. 
	// 만약 method = RequestMethod.POST 방식일 경우는 오류 발생
	@GetMapping(value="/doB") // get방식일 때 , // @PostMapping 방식도 존재	
	public void doB() {
		logger.info("doB() 실행!!!!!!!!!!!!!");
	}
	
	
}
