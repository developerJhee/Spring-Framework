package com.itwillbs.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoPasswordEncoder implements PasswordEncoder{
	
	private static final Logger mylog = LoggerFactory.getLogger(CustomNoPasswordEncoder.class);
	
	@Override
	public String encode(CharSequence rawPassword) {
		mylog.debug("인코딩 전 비밀번호 : " + rawPassword);
		
		return rawPassword.toString(); // 인코딩안하고 문자만 그대로 리턴
	}
	
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		mylog.debug("인코딩 매칭 : " + rawPassword+","+ encodedPassword);
		
		return rawPassword.toString().equals(encodedPassword); // 값이 같은지만 비교
	}
}
