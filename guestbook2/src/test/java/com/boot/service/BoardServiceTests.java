package com.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;

@SpringBootTest
public class BoardServiceTests {
	
	@Autowired
	BoardService boardService;
	
	/*@Test
	public void testRegister() {
		BoardDto dto = BoardDto.builder()
				.title("Test...")
				.content("Test... content...")
				.writerEmail("user101@aaa.com")
				.build();
		
		Long bno = boardService.register(dto);
	}*/
	
	/*@Test
	public void testList() {
		
		PageRequestDto pageRequestDto = new PageRequestDto();
		
		PageResultDto<BoardDto, Object[]> result = 
				boardService.getList(pageRequestDto);
		
		for(BoardDto boardDto : result.getDtoList()) {
			System.out.println(boardDto);
		}
	}*/
	
	@Test
	public void testGet() {
		Long bno = 100L;
		BoardDto boardDto = boardService.get(bno);
		System.out.println(boardDto);
	}
		
}





