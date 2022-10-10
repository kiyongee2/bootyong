package com.boot.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.ReplyDto;

@SpringBootTest
public class ReplyServiceTests {
	
	@Autowired
	ReplyService service;
	
	@Test
	public void testGetList() {
		Long bno = 295L;
		
		List<ReplyDto> replyDtoList = service.getList(bno);
		
		replyDtoList.forEach(replyDto -> System.out.println(replyDto));
	}
}
