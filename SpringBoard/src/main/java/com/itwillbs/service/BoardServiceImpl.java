package com.itwillbs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

@Service // 서비스 객체로 인식
public class BoardServiceImpl implements BoardService {
	
	private static final Logger mylog 
					= LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired // dao 객체 주입
	private BoardDAO dao;
	
	
	
	/**
	 * 글쓰기 동작 오버라이딩
	 */
	@Override
	public void insertBoard(BoardVO vo) throws Exception {
		mylog.debug("insertBoard(BoardVO vo) 호출 -> DAO 동작 호출 ");
		
		dao.createBoard(vo);
		
		mylog.debug("글쓰기 완료 -> 컨트롤러 이동");
	}
	
	
	
	
	/**
	 * 글목록 오버라이딩
	 */
	@Override
	public List<BoardVO> getBoardListAll() throws Exception {
		mylog.debug(" getBoardListAll() - DAO 호출(결과 리턴받기) ");
		
		return dao.getBoardListAll();
	}
	
	
	
	/**
	 *  조회수 1증가
	 */
	@Override
	public void updateViewcnt(Integer bno) throws Exception {
		mylog.debug(" updateViewcnt(Integer bno) - DAO 호출( 조회수 1증가 ) ");
		dao.updateViewcnt(bno);		
	}

	
	
	
	/**
	 *  글번호(bno)를 사용한 정보 조회
	 */
	@Override
	public BoardVO getBoard(Integer bno) throws Exception{
		mylog.debug("getBoard(Integer bno) 호출");
		return dao.getBoard(bno);
	}
	
	
	/**
	 * 글 정보수정
	 */
	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		mylog.debug(" updateBoard(BoardVO vo) ");
		return dao.updateBoard(vo);
	}
	
	
	
	/**
	 * 글 정보삭제
	 */
	@Override
	public void deleteBoard(Integer bno) throws Exception {
		 dao.deleteBoard(bno);
	}
	
}
