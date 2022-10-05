package com.boot.service;

import com.boot.domain.Member;

public interface MemberService {
	
	Member getMember(Member member);  //로그인
	
	void signup(Member member);  //회원 가입
}
