package com.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.GuestbookDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Guestbook;
import com.boot.service.GuestbookService;

@SpringBootTest
public class GuestbookServiceTest {
	
	@Autowired
	private GuestbookService service;
	
	/*@Test
	public void testRegister() {
		GuestbookDto guestbookDto = GuestbookDto.builder()
				.title("Sample Title")
				.content("Sample Content..")
				.writer("user0")
				.build();
				
		System.out.println(service.register(guestbookDto));
	}*/
	
	/*@Test
	public void testList() {
		PageRequestDto pageRequestDto = PageRequestDto.builder()
				.page(1).size(10).build();
		
		PageResultDto<GuestbookDto, Guestbook> resultDto = 
				service.getList(pageRequestDto);
		
		System.out.println("PREV: " + resultDto.isPrev());
		System.out.println("NEXT: " + resultDto.isNext());
		System.out.println("TOTAL: " + resultDto.getTotalPage());
		
		System.out.println("========================================");
		for(GuestbookDto guestbookDto : resultDto.getDtoList()) {
			System.out.println(guestbookDto);
		}
		
		System.out.println("========================================");
		resultDto.getPageList().forEach(i -> System.out.print(i + " "));
	}*/
	
	@Test
	public void testSearch() {
		PageRequestDto pageRequestDto = PageRequestDto.builder()
				.page(1)
				.size(10)
				.type("tc")
				.keyword("가을")
				.build();
		
		PageResultDto<GuestbookDto, Guestbook> resultDto = 
				service.getList(pageRequestDto);
		
		System.out.println("PREV: " + resultDto.isPrev());
		System.out.println("NEXT: " + resultDto.isNext());
		System.out.println("TOTAL: " + resultDto.getTotalPage());
		
		System.out.println("========================================");
		for(GuestbookDto guestbookDto : resultDto.getDtoList()) {
			System.out.println(guestbookDto);
		}
		
		System.out.println("========================================");
		resultDto.getPageList().forEach(i -> System.out.print(i + " "));
	}
	
}
