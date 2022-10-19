package com.boot.repository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.boot.entity.Board;
import com.boot.entity.Member;

@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	BoardRepository boardRepo;
	
	//100명이 게시글 100개 쓰기
	@Test
	public void insertBoard() {
		IntStream.rangeClosed(1, 20).forEach(i -> {
			Member member = Member.builder().userid("user" + i).build();
			
			Board board = Board.builder()
					.title("Title " + i)
					.content("Content " + i)
					.writer(member)
					.cnt(0L)
					.build();
			
			boardRepo.save(board);
			
		});
	}
	
	//게시글과 작성자를 조회
	/*@Transactional
	@Test
	public void testRead1() {
		Optional<Board> result = boardRepo.findById(100L);
		
		Board board = result.get();
		
		System.out.println(board);
		System.out.println(board.getWriter());
	}*/
	
	//게시글 작성한 회원 조회
	/*@Test
	public void testReadWithWriter() {
		Object result = boardRepo.getBoardWithWriter(100L);
		
		//회원은 여러 개의 글을 작성
		Object[] arr = (Object[]) result;
		
		System.out.println(Arrays.toString(arr));
	}*/
	
	//게시글, 회원(작성자), 댓글 수 조회(목록)
	/*@Test
	public void testWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Object[]> result = boardRepo.getBoardWithReplyCount(pageable);
		
		result.get().forEach(row -> {
			
			Object[] arr = (Object[])row;
			System.out.println(Arrays.toString(arr));
		});
	}*/
	
	//특정 게시물 조회
	/*@Test
	public void testRead2() {
		Object result = boardRepo.getBoardByBno(1L);
		Object[] arr = (Object[]) result;
		
		System.out.println(Arrays.toString(arr));
	}*/
	
	//검색 테스트
	/*@Test
	public void testSearch1() {
		boardRepo.search1();
	}*/
	
	//검색 처리
	@Test
	public void testSearchPage() {
		Pageable pageable = 
				PageRequest.of(0, 10, Sort.by("bno").descending()
								          .and(Sort.by("title").ascending()));
		
		//제목에 1이 포함된 글을 검색
		Page<Object[]> result = boardRepo.searchPage("t", "1", pageable);
	}
}





