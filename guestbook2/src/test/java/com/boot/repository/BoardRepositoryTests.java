package com.boot.repository;

import java.util.Arrays;
import java.util.List;
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
public class BoardRepositoryTests {
	
	@Autowired
	BoardRepository boardRepository;
	
	//한 명의 사용자가 1개의 게시물 등록
	/*@Test
	public void insertBoard() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder()
					.email("user" + i + "@aaa.com").build();
			
			Board board = Board.builder()
					.title("Title... " + i)
					.content("Content..." + i)
					.writer(member)
					.build();
			
			boardRepository.save(board);
		});
	}*/
	
	/*@Test
	@Transactional  //fetch 속성 지정한 후 추가함
	public void testRead() {
		Optional<Board> result = boardRepository.findById(100L);
		Board board = result.get();
		
		System.out.println(board);
		System.out.println(board.getWriter());
	}*/
	
	/*@Test
	public void testReadWithWriter() {
		Object result = boardRepository.getBoardWithWriter(100L);
		
		Object[] arr = (Object[]) result;
		
		System.out.println(Arrays.toString(arr));
	}*/
	
	/*@Test
	public void testGetBoardWithReply() {
		List<Object[]> result = boardRepository.getBoardWithReply(100L);
		
		for(Object[] arr : result) {
			System.out.println(Arrays.toString(arr));
		}
	}*/
	
	//10개의 게시물, 회원, 댓글 수 가져오기
	/*@Test
	public void testWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		
		Page<Object[]> result = boardRepository.getBoardWithReplycount(pageable);
		
		result.get().forEach(row -> {
			Object[] arr = row;
			System.out.println(Arrays.toString(arr));
		});
	}*/
	
	//1개의 게시물, 회원, 댓글 수 가져오기
	/*@Test
	public void testRead2() {
		Object result = boardRepository.getBoardByBno(100L);
		
		Object[] arr = (Object[]) result;
		
		System.out.println(Arrays.toString(arr));
	}*/
	
	/*@Test
	public void testSearch1() {
		boardRepository.search1();
	}*/
	
	@Test
	public void testSearchPage() {
		Pageable pageable = PageRequest.of(0, 10, 
				Sort.by("bno").descending()
					.and(Sort.by("title").ascending()));
		
		Page<Object[]> result = boardRepository.searchPage("t", "1", pageable);
	}
}










