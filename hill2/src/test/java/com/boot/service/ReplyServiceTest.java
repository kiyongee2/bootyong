package com.boot.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.ReplyDto;

@SpringBootTest
public class ReplyServiceTest {

	@Autowired
	private ReplyService service;
	
	//댓글 목록 보기
	@Test
	public void testGetList() {
		Long bno = 99L;
		List<ReplyDto> dtoList = service.getList(bno);
		dtoList.forEach(replyDto -> System.out.println(replyDto));
	}
}
