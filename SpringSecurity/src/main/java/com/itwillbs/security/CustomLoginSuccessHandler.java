package com.itwillbs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler 
				implements AuthenticationSuccessHandler{
	// 로그인 후 권한에 따른 페이지 이동

	private static final Logger mylog = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		mylog.debug("onAuthenticationSuccess() 호출");
		
		// 권한정보 저장
		List<String> roleNames = new ArrayList<String>();
		
		// 람다식 쓴 코드
		// ctrl + 1 : 람다식 <-> 익명클래스 변경 단축키
		authentication.getAuthorities().forEach(authority ->{
			roleNames.add(authority.getAuthority());
		});
		
		// 람다식쓰기전 익명객체 풀어서 쓴 경우(같은코드임)
//		authentication.getAuthorities().forEach(
//				new Consumer<GrantedAuthority>() {
//					@Override
//					public void accept(GrantedAuthority authority) {
//						roleNames.add(authority.getAuthority());
//					}
//				}
//			);
		
		mylog.debug(roleNames+"");
		
		// 접근권한에 따른 페이지 이동
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/sec/admin");
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBER")) {
			response.sendRedirect("/sec/member");
			return;
		}
		
		response.sendRedirect("sec/all");
		
	}
}
