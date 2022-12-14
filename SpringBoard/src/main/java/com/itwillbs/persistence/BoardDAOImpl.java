package com.itwillbs.persistence;

import javax.inject.Inject;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
		
	private static final Logger mylog = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	// 디비연결 -> bean 주입(root-context.xml)
	@Inject
	private SqlSession sqlSession; // 디비 mybatis 연결 해제까지~
	
	// mapper NAMESPACE 정보
	private static final String NAMESPACE
		= "com.itwillbs.mapper.BoardMapper";
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(NAMESPACE + ".getTime");
	}
	
	@Override
	public void createBoard(BoardVO vo) throws Exception {
		mylog.debug("createBoard(BoardVO vo) -> mapper 동작 호출");
		
		sqlSession.insert(NAMESPACE + ".create", vo); // 두 개짜리 인자의 메서드 호출하기
		
		mylog.debug("회원가입 완료! -> 서비스");
	}
}
