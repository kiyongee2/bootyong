package com.boot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
