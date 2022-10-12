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
public class ReplyRepositoryTests {
	
	@Autowired
	ReplyRepository replyRepository;
	
	//300개의 댓글 랜덤 등록
	/*@Test
	public void insertReply() {
		//게시물은 1부터 100까지의 글 번호
		IntStream.rangeClosed(1, 300).forEach(i -> {
			long bno = (long) (Math.random() * 100) + 1;
			Board board = Board.builder().bno(bno).build();
			
			Reply reply = Reply.builder()
					.text("Reply....." + i)
					.board(board)
					.replyer("guest")
					.build();
			
			replyRepository.save(reply);
		});
	}*/
	
	/*@Transactional
	@Test
	public void readReply1() {
		Optional<Reply> result = replyRepository.findById(1L);
		Reply reply = result.get();
		
		System.out.println(reply);
		System.out.println(reply.getBoard());
	}*/
	
	@Test
	public void testListByBoard() {
		List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(
								Board.builder().bno(95L).build());
		replyList.forEach(reply -> System.out.println(reply));
	}
	
}
