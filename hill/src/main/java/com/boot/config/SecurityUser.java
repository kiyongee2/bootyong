package com.boot.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.boot.domain.Member;

public class SecurityUser extends User{
	
	private static final long serialVersionUID = 1L;
	
	private Member member;

	public SecurityUser(Member member) {
		super(member.getUserid(), member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}
	
	//인증된 회원 정보를 html에서 사용
	public Member getMember() {
		return member;
	}
}
