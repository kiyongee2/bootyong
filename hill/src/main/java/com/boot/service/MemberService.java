package com.boot.service;

import com.boot.domain.Member;
import com.boot.dto.MemberInput;

public interface MemberService {
	
	void signup(Member member);  //회원 가입
	
	Member view(String userid);  //회원 상세 정보
	
	void update(Member member);  //회원 수정
	
	void delete(Member member);  //회원 삭제
	
	ServiceResult updatePassword(MemberInput memberInput);
}
