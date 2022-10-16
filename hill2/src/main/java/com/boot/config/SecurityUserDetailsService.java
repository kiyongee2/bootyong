package com.boot.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot.entity.Member;
import com.boot.repository.MemberRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//MemberRepository를 이용하여 조회한 회원 정보를 앞에서 만든
		//SecurityUser 객체의 파라미터로 전달하여 리턴함
		Optional<Member> optional = memberRepo.findById(username);
		if(!optional.isPresent()) { //찾은 아이디가 없으면
			throw new UsernameNotFoundException(username); //예외 발생
		}else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
	}

}
