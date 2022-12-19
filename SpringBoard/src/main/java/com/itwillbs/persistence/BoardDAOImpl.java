package com.itwillbs.persistence;

import java.util.List;

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
	
	/**
	 * 서버시간조회 오버라이딩
	 */
	@Override
	public String getTime() {
		return sqlSession.selectOne(NAMESPACE + ".getTime");
	}
	
	
	
	/**
	 * 회원가입 오버라이딩
	 */
	@Override
	public void createBoard(BoardVO vo) throws Exception {
		mylog.debug("createBoard(BoardVO vo) -> mapper 동작 호출");
		
		sqlSession.insert(NAMESPACE + ".create", vo); // 두 개짜리 인자의 메서드 호출하기
		
		mylog.debug("회원가입 완료! -> 서비스");
	}
	
	
	
	/**
	 * 글목록 오버라이딩
	 */
	@Override
	public List<BoardVO> getBoardListAll() throws Exception {		
		mylog.debug("getBoardListAll() -> sqlSession-mapper 동작 호출");
		
		List<BoardVO> boardList = sqlSession.selectList(NAMESPACE + ".listAll");
		mylog.debug("게시판 글 개수 : " + boardList.size() +"");
		
		return boardList;
	}
	
	/**
	 * 조회수 증가 오버라이딩
	 */
	@Override
	public void updateViewcnt(Integer bno) throws Exception {
		mylog.debug("updateViewcnt(Integer bno) -> sqlSession객체 ");
		
		sqlSession.update(NAMESPACE+".updateViewcnt",bno);
	}
	
	
	/**
	 * 글번호(bno)를 사용한 정보 조회 오버라이딩
	 */
	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		mylog.debug("getBoard(Integer bno) 호출 ");
		
		BoardVO vo = sqlSession.selectOne(NAMESPACE + ".getBoard", bno);		
		return vo;
	}
	
	
	
	/**
	 * 글 수정하기 오버라이딩
	 */
	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		mylog.debug(" updateBoard(BoardVO vo) ");
		return sqlSession.update(NAMESPACE + ".updateBoard", vo);
	}
	
	
	/**
	 * 글 삭제하기 오버라이딩
	 */
	@Override
	public void deleteBoard(Integer bno) throws Exception {
		mylog.debug(" deleteBoard(Integer bno) ");
		sqlSession.delete(NAMESPACE + ".deleteBoard", bno);
	}
}




