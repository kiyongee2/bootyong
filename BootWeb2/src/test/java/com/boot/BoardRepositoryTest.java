package com.boot;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	//글 등록
	@Test
	public void testInsertBoard() {
		Board board = new Board();
		board.setTitle("첫 번째 게시글");
		board.setWriter("테스터");
		board.setContent("등록이 잘 되네요..");
		board.setCreateDate(new Date());
		board.setCnt(0L);
		
		boardRepo.save(board);  //save() 메서드로 DB에 저장함
	}
	
	//상세 조회
	/*@Test
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		log.info(board.toString());
	}*/
	
	//글 수정
	/*@Test
	public void testUpdateBoard() {
		log.info("2번 게시글 조회");
		//2번 게시글 가져옴
		Board board = boardRepo.findById(2L).get();
		
		log.info("2번 게시글 제목 수정");
		board.setTitle("제목을 수정합니다");
		//수정후 저장
		boardRepo.save(board);
	}*/
	
	//글 삭제
	/*@Test
	public void testDeleteBoard() {
		
		log.info("1번 게시글 삭제");
		
		boardRepo.deleteById(1L);
	}*/
}














