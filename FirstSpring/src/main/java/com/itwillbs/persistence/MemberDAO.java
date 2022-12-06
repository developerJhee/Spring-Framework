package com.itwillbs.persistence;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {
	// 인터페이스 DAO에서의 역할 : 필요한 기능을 정의 (추상메서드)
	
	// 디비서버의 시간정보 조회
		public String getServerTime();
		
	// 회원가입
		public void insertMember(MemberVO vo);
		
	// 특정 회원정보 조회
	public MemberVO getMember(String userid);
		
	// 로그인 처리
	public MemberVO loginMember(String userid, String userpw);
	public MemberVO loginMember(MemberVO vo);
	
}
