package com.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.boot.entity.Board;
import com.boot.entity.Member;
import com.boot.entity.Role;
import com.boot.repository.BoardRepository;
import com.boot.repository.MemberRepository;

@SpringBootTest
public class DataInitializeTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder pwencoder;
	
	//회원 및 게시글 등록
	/*@Test
	public void testInsert() {
		//회원
		Member member1 = new Member();
		member1.setUserid("member");
		member1.setPassword(pwencoder.encode("member12"));  //비밀번호 암호화
		member1.setName("김기용");
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);
		memberRepo.save(member1);
		
		//관리자
		Member member2 = new Member();
		member2.setUserid("admin2");
		member2.setPassword(pwencoder.encode("admin12"));  //비밀번호 암호화
		member2.setName("관리자");
		member2.setRole(Role.ROLE_ADMIN);
		member2.setEnabled(true);
		memberRepo.save(member2);
		
		//회원이 등록한 게시글
		for(int i=1; i<=10; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle(member1.getName() + "이 등록한 게시글 " + i);
			board.setContent(member1.getName() + "이 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
		//관리자가 등록한 게시글
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle(member2.getName() + "이 등록한 게시글 " + i);
			board.setContent(member2.getName() + "이 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
	}//testInsert() 끝*/
}
