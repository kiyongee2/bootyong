package com.boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.domain.Member;
import com.boot.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepo;

	@Override
	public Member getMember(Member member) {
		//findById의 반환형이 Optional<T> 임
		//Optional은 주로 null 에러를 방지하기 위한 클래스임
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if(findMember.isPresent()) {
			return findMember.get();
		}else {
			return null;
		}
	}
}
