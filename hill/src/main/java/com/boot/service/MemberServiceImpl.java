package com.boot.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.domain.Member;
import com.boot.domain.Role;
import com.boot.dto.MemberInput;
import com.boot.dto.ServiceResult;
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
	@Transactional
	@Override
	public void update(Member member) {
		String encPW = pwencoder.encode(member.getPassword());
		member.setPassword(encPW);
		member.setRole(Role.MEMBER);
		member.setEnabled(true);
		memberRepo.save(member);
	}

	//회원 삭제
	@Override
	public void delete(Member member) {
		memberRepo.delete(member);
	}

	@Override
	public ServiceResult updatePassword(MemberInput parameter) {
		String userid = parameter.getUserid();
		
		Optional<Member> optionalMember = memberRepo.findById(userid);
		
		Member member = optionalMember.get();
		
		String encPassowrd = BCrypt.hashpw(parameter.getNewPassword(), BCrypt.gensalt());
		member.setPassword(encPassowrd);
		memberRepo.save(member);
		
		return new ServiceResult(true);
	}
}
