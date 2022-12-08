package com.itwillbs.persistence;


import java.util.List;

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
	
	// 회원정보 수정
	// 보통 정수 데이터(기본형int)를 리턴타입으로 해서 사용했었는데 스프링에서는 
	// 데이터 안정성에 따른 문제 때문에 int(기본형) -> Integer(참조형)
	// 자바의 버전이 내려가면 우리가 또 수정해야하는 번거로움이 생겨서 우리는 참조형으로 쓴다.
	public Integer updateMember(MemberVO uvo);
	
	// 회원정보 삭제
	public void deleteMember(MemberVO dvo);
	
	// 회원목록 조회
	public List<MemberVO> getMemberList(String id);
}
