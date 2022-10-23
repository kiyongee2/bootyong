package com.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;

@Transactional
@SpringBootTest
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder pwencoder;
	
	public Member createMember() {
		MemberFormDto memberFormDto = new MemberFormDto();
		memberFormDto.setEmail("test@test.com");
		memberFormDto.setName("김기용");
		memberFormDto.setAddress("서울시 은평구 불광동");
		memberFormDto.setPassword("12345");
		return Member.createMember(memberFormDto, pwencoder);
		
	}
	
	//회원 가입
	/*@Test
	public void saveMemberTest() {
		Member member = createMember();
		Member savedMember = memberService.saveMember(member);
		
		assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getAddress(), savedMember.getAddress());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getRole(), savedMember.getRole());
	}*/
	
	//중복 회원 가입 테스트
	@Test
	public void saveDuplicateMemberTest() {
		Member member1 = createMember();
		Member member2 = createMember();
		memberService.saveMember(member1);
	
		Throwable e = assertThrows(IllegalStateException.class, () -> {
			memberService.saveMember(member2);
		});
	
		assertEquals("이미 가입된 회원입니다", e.getMessage());
	}
}










