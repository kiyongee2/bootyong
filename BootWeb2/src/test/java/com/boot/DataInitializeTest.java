package com.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.domain.Member;
import com.boot.persistence.BoardRepository;
import com.boot.persistence.MemberRepository;

@SpringBootTest
public class DataInitializeTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testDataInsert() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("뽀로로");
		member1.setRole("ROLE_USER");
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("아기상어");
		member2.setRole("ROLE_ADMIN");
		memberRepo.save(member2);
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setWriter("뽀로로");
			board.setTitle("뽀로로가 등록한 게시글 " + i);
			board.setContent("뽀로로가 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setWriter("아기상어");
			board.setTitle("아기상어가 등록한 게시글 " + i);
			board.setContent("아기상어가 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
	}
}
