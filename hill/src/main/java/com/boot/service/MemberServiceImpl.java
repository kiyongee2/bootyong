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

	//회원 가입
	@Override
	public void signup(Member member) {
		String encPW = pwencoder.encode(member.getPassword());
		member.setPassword(encPW);
		member.setRole(Role.MEMBER);
		member.setEnabled(true);
		memberRepo.save(member);
	}

	//회원 상세 정보
	@Override
	public Member view(String userid) {
		return memberRepo.findById(userid).get();
	}

	//회원 수정
	@Override
	public void update(Member member) {
		memberRepo.save(member);
	}

	//회원 삭제
	@Override
	public void delete(Member member) {
		memberRepo.delete(member);
	}
}
