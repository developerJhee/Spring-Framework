package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
// @Service : 스프링이 해당파일을 서비스 객체(bean)로 인식

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	// 컨트롤러 - 서비스 - DAO 연결
	// 수행해야하는 동작 구현
	
	private static final Logger mylog = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	// DAO 객체 필요함
	@Inject
	private MemberDAO dao;
	
	@Override
	public void memberJoin(MemberVO vo) {
		mylog.info("컨트롤러 호출 ->  서비스 -> DAO 호출");
		dao.insertMember(vo);
		mylog.info("DAO 호출 ->  서비스 -> 컨트롤러에게 정보 전달완");
	}
	
	@Override
	public boolean memberLogin(MemberVO vo) {
		mylog.debug("memberLogin(vo) 호출");
		
		MemberVO resultVO = dao.loginMember(vo);
		
		// resultVO가 null인지 아닌지 판단
		// null이면 return false;
		// null이 아니면 return true;		
		mylog.debug(" DAO 처리 결과 : " + resultVO);
//		if(resultVO != null) 
//			return true;
//		else
//			return false;
		
		return (resultVO != null)? true:false; // if대신 삼항연산자 사용
	}
	
	
	@Override	// 오버라이딩하면 부모메서드는 은닉되고 해당 메서드를 호출할 수 있다.
	public MemberVO getMember(String id) {
//		MemberVO vo = dao.getMember(id); // 매개변수에 불과하기때문에 userid -> id
//		return vo;
		
		return dao.getMember(id);
	}
	
	@Override
	public Integer updateMember(MemberVO uvo) {
		mylog.debug(" updateMember(uvo) ");
		
		return dao.updateMember(uvo);
	}
	
	
	@Override
	public void deleteMember(MemberVO dvo) {
		dao.deleteMember(dvo);
	}
	
	@Override
	public List<MemberVO> getMemList(String id) {
		
		return dao.getMemberList(id);
	}
	
}
