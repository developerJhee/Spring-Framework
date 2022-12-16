package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {
	
	// 게시판 글쓰기
	public void insertBoard(BoardVO vo) throws Exception; 
						// => 예외던지기, 전달인자 주기
	
	// 게시판 글목록(all)
	public List<BoardVO> getBoardListAll() throws Exception; 
	
	// 글 조회수 1증가
	public void updateViewcnt(Integer bno) throws Exception;
}
