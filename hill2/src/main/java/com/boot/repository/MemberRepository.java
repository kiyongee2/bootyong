package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.boot.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	//ID 중복 확인
	@Query("SELECT COUNT(*) FROM Member m WHERE m.userid = :userid")
	public int checkID(String userid);
}
