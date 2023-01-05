package com.itwillbs.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

// 접근권한시 뷰페이지 처리 - 시큐리티 설정( error-page="/accessErr")
// 접근권한 제한시 특정 동작을 처리할때(쿠키,세션제어) 직접 객체를 생성
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

private static final Logger mylog = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		mylog.debug(" handle() 실행 ");
//		
//		HttpSession session = request.getSession();
//		session.invalidate();
//		mylog.debug(" handle() 세션정보 초기화!");
		mylog.debug(" 사용자가 정의한 에러페이지(/accessErr) 연결중 ");
		
		response.sendRedirect("/accessErr");
	}
}
