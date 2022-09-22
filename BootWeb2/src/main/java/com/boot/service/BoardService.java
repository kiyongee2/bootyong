package com.boot.service;

import java.util.List;

import com.boot.domain.Board;

public interface BoardService {
	
	List<Board> getBoardList();
	
	void insertBoard(Board board);
	
	Board getBoard(long seq);
	
	void updateBoard(Board board);
	
	void deleteBoard(Board board);
}
