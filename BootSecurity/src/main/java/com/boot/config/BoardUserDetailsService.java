package com.boot.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot.domain.Member;
import com.boot.persistence.MemberRepository;

@Service
public class BoardUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepo;

	//loadUserByUsername() 재정의 함
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//사용자 아이디를 매개변수로 받아서 회원 정보를 조회하고,
		//조회 결과를 UserDetails 타입의 객체로 리턴한다.
		Optional<Member> optional = memberRepo.findById(username);
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(username + "사용자 없음");
		}else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
	}
}
