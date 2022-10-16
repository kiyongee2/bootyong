package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
