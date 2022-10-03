package com.boot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	//조회수
	@Modifying
	@Query("UPDATE Board b SET b.cnt = b.cnt + 1 WHERE b.seq = :seq")
	void updateCount(Long seq);
}
