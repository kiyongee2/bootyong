package com.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.shop.dto.BoardSearchDto;
import com.shop.dto.ItemSearchDto;
import com.shop.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>,
QuerydslPredicateExecutor<Board>, BoardRepositoryCustom{
	
	@Query("SELECT b FROM Board b ORDER BY b.id DESC")
	List<Board> findAllDesc();
	
	//์กฐํ์
	@Modifying
	@Query("UPDATE Board b SET b.cnt = b.cnt + 1 WHERE b.id = :id")
	void updateCount(Long id);
	
	Page<Board> getBoardPage(BoardSearchDto BoardSearchDto, Pageable pageable);
}
