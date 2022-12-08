package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : 스프링에서 해당파일은을 DAO 파일로 인식시키는 용도

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	private static final Logger mylog = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// 디비 연결정보 필요함 => 의존관계 발생	
	@Inject
	private SqlSession sqlSession; // 디비연결+mybatis+sql-mapper+자원해제 가능
	
	
	// mapper의 namespace 정보 저장
	private static final String NAMESPACE 
				= "com.itwillbs.mapper.MemberMapper";  
	
	@Override
	public String getServerTime() {
		// 디비연결
		// sql작성&pstmt
		// ??
		// sql 실행
		// 데이터처리
		
//		sqlSession.selectOne(쿼리); // 리턴타입 T : ﻿제네릭
		// 쿼리는 어디에 있음? mapper에 있다. => namespace 복사해오기
//		sqlSession.selectOne("com.itwillbs.mapper.MemberMapper"); // 리턴타입 T : ﻿제네릭
			//com.itwillbs.mapper.MemberMapper는 sql구문이아니다. 실제 sql구문은
			// mapper안에 <select> 태그안에 있는 코드이다. 그렇기 때문에
		String time = 
				sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime"); // 이렇게 적어둔다.
				
	
		return time;
	}

	@Override
	public void insertMember(MemberVO vo) {
//		System.out.println(" DAO : " + vo);
		mylog.info("서비스 -> DAO -> mapper");
		// 디비연결 - sql 작성 - 실행
		sqlSession.insert(NAMESPACE + ".createMember", vo); // 얘가 mapper 부른거임
		// 일반적으로 statement에 namespace.id 를 적었었다. 근데 이거는 안정적이지못하다 name이 바뀔수도있으니까 그래서 위에 매퍼 선언해줌
//		System.out.println(" DAOImpl : 회원가입 성공! ");  // vo 값 넘겨줘야해서 꼭 뒤어 적어주기
		mylog.info("mapper -> DAO -> 서비스");
	}
	
	@Override
	public MemberVO getMember(String userid) {
					//"com.itwillbs.mapper.MemberMapper.getTime" 원래 이렇게 적어야하는데 
					//"com.itwillbs.mapper.MemberMapper"까지는 상수로 만들어놓고
					//"com.itwillbs.mapper.MemberMapper.[ID]"
		MemberVO vo = sqlSession.selectOne(NAMESPACE + ".getMember", userid); // 업캐스팅가능하다는 의미 (둘의 타입은 동일하다)
		
		System.out.println(" DAO : " + vo);
		
		return vo;
	}

	
	@Override
	public MemberVO loginMember(String userid, String userpw) {
//		sqlSession.selectOne(statement,userid,userpw); (X)
//		sqlSession.selectOne(statement, vo); (O) vo 객체 생성해서 set호출 저장
		
		// VO객체 안에 전달된 정보를 한 번에 전달 불가능한 경우
		// COLLECTION객체 map을 사용할 거다.
		// -> 관련없는 데이터를 1개 이상 전달하는 경우(join같은 경우에 의해 어쩔 수 없이)
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		// paramMap.put("mapper"에 매핑될 이름, userid);
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		MemberVO vo 
	      = sqlSession.selectOne(NAMESPACE+".loginMember",paramMap);
	
			return vo;
	}
	
	@Override
	public MemberVO loginMember(MemberVO vo) {
		// 관련있는 애들은 vo에 담아서 이렇게 보내는게 제일 best
		return sqlSession.selectOne(NAMESPACE + ".loginMember", vo);
		
	}
	
	
	@Override
	public Integer updateMember(MemberVO uvo) {
		mylog.info(" updateMember(uvo) 호출 ");
		// mapper 호출 -> 실행
		
		return sqlSession.update(NAMESPACE + ".updateMember", uvo);
		// 값들은 0 아니면 1이다.
	}
	
	
	@Override
	public void deleteMember(MemberVO dvo) {
		sqlSession.delete(NAMESPACE + ".deleteMember", dvo);
		
	}
	
	@Override
	public List<MemberVO> getMemberList(String id) {
		
		// 돌아가면서 보다가 id에 해당하는 모든 정보를 조회하고 그 정보를 dto에 담아서 
		// 모은 회원정보 dto들을 list형태로 뽑아낸다.
		// selectList() : mapper에서 VO형태로 리턴된 데이터를 
		// 				  자동으로 List형태로 변경
		
		return sqlSession.selectList(NAMESPACE + ".getMemberList", id);
		// 객체에 안 담고 그냥 바로 리턴문에 적었음
	}
	
}
