package com.boot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.entity.Board;
import com.boot.repository.search.SearchBoardRepository;

public interface BoardRepository extends JpaRepository<Board, Long>,
SearchBoardRepository{
	
	//회원 - 1개의 Object 내에 Object[]로 나옴
	@Query("SELECT b, w FROM Board b LEFT JOIN b.writer w WHERE b.bno = :bno")
	Object getBoardWithWriter(@Param("bno") Long bno);
	
	//게시글(번호, 제목, 작성 시간)
	@Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno")
	List<Object[]> getBoardWithReply(@Param("bno") Long bno);
	
	//댓글(댓글수)
	@Query(value = "SELECT b, w, count(r) "
			+ "FROM Board b "
			+ "LEFT JOIN b.writer w "
			+ "LEFT JOIN Reply r ON r.board = b "
			+ "GROUP BY b",
			countQuery = "SELECT count(b) FROM Board b")
	Page<Object[]> getBoardWithReplycount(Pageable pageable);
	
	//특정 게시물 번호 사용
	@Query("SELECT b, w, count(r) "
			+ "FROM Board b LEFT JOIN b.writer w "
			+ "LEFT OUTER JOIN Reply r ON r.board = b "
			+ "WHERE b.bno = :bno")
	Object getBoardByBno(@Param("bno") Long bno);
}




