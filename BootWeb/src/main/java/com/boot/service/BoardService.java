package com.boot.service;

import java.util.List;

import com.boot.domain.Board;

public interface BoardService {
	
	List<Board> getBoardList();  //목록 보기
	
	void insertBoard(Board board);  //새글 등록
	
	Board getBoard(long seq);  //글 상세보기
	
	void updateBoard(Board board); //글 수정하기
	
	void deleteBoard(Board board); //글 삭제하기
	
	void updateCnt(Long seq);  //조회수 
}
