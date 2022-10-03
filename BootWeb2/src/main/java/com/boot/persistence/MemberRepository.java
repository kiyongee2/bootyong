package com.boot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	//ID 중복 확인
	@Query("SELECT COUNT(*) FROM Member m WHERE m.id = :id")
	public int checkID(String id);
}
