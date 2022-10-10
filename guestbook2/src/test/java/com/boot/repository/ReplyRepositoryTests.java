package com.boot.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.entity.Board;
import com.boot.entity.Reply;

@SpringBootTest
public class ReplyRepositoryTests {
	
	@Autowired
	ReplyRepository replyRepository;
	
	/*@Test
	public void insertReply() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			long bno = (long) (Math.random() * 100) + 201;
			Board board = Board.builder().bno(bno).build();
			
			Reply reply = Reply.builder()
					.text("Reply....." + i)
					.board(board)
					.replyer("guest")
					.build();
			
			replyRepository.save(reply);
		});
	}*/
	
	/*@Test
	public void readReply1() {
		Optional<Reply> result = replyRepository.findById(60L);
		Reply reply = result.get();
		
		System.out.println(reply);
		System.out.println(reply.getBoard());
	}*/
	
	@Test
	public void testListByBoard() {
		List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(
								Board.builder().bno(295L).build());
		replyList.forEach(reply -> System.out.println(reply));
	}
	
}
