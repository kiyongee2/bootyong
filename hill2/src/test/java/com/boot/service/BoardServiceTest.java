package com.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;

@SpringBootTest
public class BoardServiceTest {
	
	@Autowired
	private BoardService boardService;
	
	//게시글 등록
	/*@Test
	public void testRegister() {
		BoardDto dto = BoardDto.builder()
				.title("한글 테스트1")
				.content("테스트 내용입니다.")
				.writerUserid("user11")
				.build();
		
		boardService.register(dto);
	}*/
	
	//게시글 목록 보기
	/*@Test
	public void testList() {
		
		PageRequestDto pageRequestDto = new PageRequestDto();
		
		PageResultDto<BoardDto, Object[]> result = boardService.getList(pageRequestDto);
		
		for(BoardDto boardDto : result.getDtoList()) {
			System.out.println(boardDto);
		}
	}*/
	
	//게시글 상세 보기
	/*@Test
	public void testGet() {
		Long bno = 90L;
		BoardDto boardDto = boardService.get(bno);
		
		System.out.println(boardDto);
	}*/
	
	//게시글 수정
	@Test
	public void testModify() {
		BoardDto boardDto = BoardDto.builder()
				.bno(10L)
				.title("제목 수정")
				.content("내용을 수정합니다.")
				.build();
		boardService.modify(boardDto);
	}
}





