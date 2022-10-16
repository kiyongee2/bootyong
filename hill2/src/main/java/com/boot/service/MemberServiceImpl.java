package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.entity.Member;
import com.boot.entity.Role;
import com.boot.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder pwencoder;

	@Override
	public void signup(Member member) {
		//비밀번호 암호화
		String encPW = pwencoder.encode(member.getPassword());
		member.setPassword(encPW);
		member.setRole(Role.ROLE_ADMIN); //권한 설정
		member.setEnabled(true);   //계정 있음
		memberRepo.save(member);
	}

	@Override
	public Member view(String userid) {
		return memberRepo.findById(userid).get();
	}

	//회원 정보 수정
	@Override
	public void update(Member member) {
		//수정시에도 비밀번호 암호화, 권한, 계정은 필수 저장
		String encPW = pwencoder.encode(member.getPassword());
		member.setPassword(encPW);
		member.setRole(Role.ROLE_MEMBER); //권한 설정
		member.setEnabled(true);   //계정 있음
		memberRepo.save(member);
	}

	//회원 정보 삭제
	@Override
	public void delete(Member member) {
		memberRepo.delete(member);
	}
}
