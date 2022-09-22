package com.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepo;

	//게시글 목록 보기
	@Override
	public List<Board> getBoardList() {
		return (List<Board>) boardRepo.findAll();
	}

	//게시글 등록
	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	//게시글 상세 조회
	@Override
	public Board getBoard(long seq) {
		return boardRepo.findById(seq).get();
	}

	//게시글 수정
	@Override
	public void updateBoard(Board board) {	
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	//게시글 삭제
	@Override
	public void deleteBoard(Board board) {
		boardRepo.delete(board);
	}

}
