package com.shop.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService{
	//MemberService가 UserDetailService를 구현
	private final MemberRepository memberRepo;

	//회원 저장
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepo.save(member);
	}

	//중복 체크
	public void validateDuplicateMember(Member member) {
		Member findMember = memberRepo.findByEmail(member.getEmail());
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}

	//로그인 서비스 loadUserByUsername(String email) 재정의
	//User 객체 반환 - email, password, roles를 파라미터로 넘겨줌
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepo.findByEmail(email);
		
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
	
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}
}
