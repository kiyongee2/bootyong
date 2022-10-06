package com.boot;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.entity.Guestbook;
import com.boot.repository.GuestbookRepository;

@SpringBootTest
public class GuestbookRepositoryTest {
	
	@Autowired
	private GuestbookRepository guestbookRepo;
	
	/*@Test
	public void insertData() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			
			Guestbook guestbook = Guestbook.builder()
					.title("Title..." + i)
					.content("Content..." + i)
					.writer("user" + (i % 10))
					.build();
			
			System.out.println(guestbookRepo.save(guestbook));
		});
	}*/
	
	@Test
	public void updateTest() {
		Optional<Guestbook> result = guestbookRepo.findById(300L);
		
		if(result.isPresent()) {
			Guestbook guestbook = result.get();
			
			guestbook.changeTitle("제목 변경..");
			guestbook.changeContent("내용 변경..");
			
			guestbookRepo.save(guestbook);
		}
	}
	
	
}







