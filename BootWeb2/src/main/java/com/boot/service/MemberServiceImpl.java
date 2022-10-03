package com.boot.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.domain.Member;
import com.boot.persistence.MemberRepository;

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
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if(findMember.isPresent()) {
			return findMember.get();
		}else {
			return null;
		}
	}

	@Override
	public void signup(Member member) {
		//비밀번호 암호화
		String encPW = pwencoder.encode(member.getPassword());
		member.setPassword(encPW);
		member.setRole("ROLE_USER");
		memberRepo.save(member);
	}

	@Override
	public Member getMember(String id) {
		return memberRepo.findById(id).get();
	}

	@Override
	public void delete(Member member) {
		memberRepo.delete(member);
	}

	@Override
	public void update(Member member) {
		//비밀번호 암호화
		String encPW = pwencoder.encode(member.getPassword());
		member.setPassword(encPW);
		memberRepo.save(member);
	}

	//@Transactional
	@Override
	public int checkID(String id) {
		return memberRepo.checkID(id);
	}
}
