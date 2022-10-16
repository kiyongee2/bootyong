package com.boot.service;

import com.boot.entity.Member;

public interface MemberService {
	
	void signup(Member member);  //회원 가입
	
	Member view(String userid);  //상세 보기
	
	void update(Member member);  //회원 정보 수정
	
	void delete(Member member);  //회원 정보 삭제
}
