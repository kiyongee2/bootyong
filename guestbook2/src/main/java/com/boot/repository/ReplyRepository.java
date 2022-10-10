package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.boot.entity.Board;
import com.boot.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
	
	//Board 삭제시에 댓글들 삭제
	@Modifying
	@Query("DELETE FROM Reply r WHERE r.board.bno = :bno")
	void deleteByBno(Long bno);
	
	//게시물로 댓글 목록 보기
	List<Reply> getRepliesByBoardOrderByRno(Board board);
}
