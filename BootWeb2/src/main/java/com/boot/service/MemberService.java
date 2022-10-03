package com.boot.service;

import com.boot.domain.Member;

public interface MemberService {
	
	Member getMember(Member member);  //로그인
	
	void signup(Member member);  //회원 가입
	
	Member getMember(String id);  //회원 상세 정보
	
	void delete(Member member);  //회원 탈퇴
	
	void update(Member member);  //회원 수정
	
	int checkID(String id);  //ID 중복 체크
}
