package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	//중복 이메일 검사
	Member findByEmail(String email);  
}
