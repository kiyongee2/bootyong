package com.boot.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.entity.Member;
import com.boot.entity.Role;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepo;
	
	//회원 100명 생성
	@Test
	public void insertMember() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			
			Member member = Member.builder()
					.userid("user" + i)
					.password("1234")
					.name("user" + i)
					.role(Role.ROLE_MEMBER)
					.enabled(true)
					.build();
			
			memberRepo.save(member);
		});
	}
}




