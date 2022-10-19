package com.boot.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.entity.Board;
import com.boot.entity.Reply;

@SpringBootTest
public class ReplyRepositoryTest {

	@Autowired
	ReplyRepository replyRepo;
	
	//300개의 댓글 생성
	@Test
	public void insertReply() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			//게시글 번호(bno)를 랜덤하게 생성 - 1부터 100까지
			Long bno = (long)(Math.random() * 100) + 1;
			Board board = Board.builder().bno(bno).build();
			
			Reply reply = Reply.builder()
					.text("Reply " + i)
					.board(board)
					.replyer("guest")
					.build();
			
			replyRepo.save(reply);
		});
	}
	
	//댓글 조회
	/*@Transactional  //지연 로딩 시 사용함
	@Test
	public void readReply() {
		Optional<Reply> result = replyRepo.findById(1L);
		
		Reply reply = result.get();
		
		System.out.println(reply);
		System.out.println(reply.getBoard());
	}*/
	
	//댓글 목록
	/*@Test
	public void testListByBoard() {
		//100번 게시글 생성
		Board board = Board.builder().bno(100L).build();
		
		List<Reply> replyList = replyRepo.getRepliesByBoardOrderByRno(board);
		
		replyList.forEach(reply -> System.out.println(reply));
	}*/
}




