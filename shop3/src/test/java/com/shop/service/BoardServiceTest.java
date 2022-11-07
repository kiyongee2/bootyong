package com.shop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.dto.BoardDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardServiceTest {
	
	@Autowired
	BoardService boardService;
	
	/*@Test
	public void testRegister() {
		
		BoardDto dto = BoardDto.builder()
				.title("한글 테스트")
				.content("테스트 내용입니다.")
				.cnt(0L)
				.build();
		boardService.register(dto);
	}*/
	
	
}
