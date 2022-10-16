package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.boot.entity.Board;
import com.boot.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
	//댓글 목록 가져오기
	List<Reply> getRepliesByBoardOrderByRno(Board board);
	
	//게시글 삭제
	@Modifying
	@Query("DELETE FROM Reply r WHERE r.board.bno = :bno")
	void deleteByBno(Long bno);
}
