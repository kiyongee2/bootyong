package com.boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.domain.Member;
import com.boot.domain.Role;
import com.boot.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder pwencoder;


	@Override
	public Member getMember(Member member) {
		//findById의 반환형이 Optional<T> 임
		//Optional은 주로 null 에러를 방지하기 위한 클래스임
		Optional<Member> findMember = memberRepo.findById(member.getUserid());
		if(findMember.isPresent()) {
			return findMember.get();
		}else {
			return null;
		}
	}

	@Override
	public void signup(Member member) {
		String encPW = pwencoder.encode(member.getPassword());
		member.setPassword(encPW);
		member.setRole(Role.MEMBER);
		memberRepo.save(member);
	}

}
