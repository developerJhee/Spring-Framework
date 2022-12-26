package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardService {
	
	// 게시판 글쓰기
	public void insertBoard(BoardVO vo) throws Exception; 
						// => 예외던지기, 전달인자 주기
	
	// 게시판 글목록(all)
	public List<BoardVO> getBoardListAll() throws Exception; 
	
	// 글 조회수 1증가
	public void updateViewcnt(Integer bno) throws Exception;
	
	// 글번호(bno)를 사용한 정보 조회
	public BoardVO getBoard(Integer bno) throws Exception;
	
	// 글정보 수정
	public Integer updateBoard(BoardVO vo) throws Exception;
	
	// 글정보 삭제
	public void deleteBoard(Integer bno) throws Exception;
	
	// 글정보조회(페이징처리cri)
	public List<BoardVO> getListPage(Criteria cri) throws Exception;
	
	// 글 전체 개수
	public int totalCnt() throws Exception;
}
