package com.boot.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Board;
import com.boot.entity.Member;
import com.boot.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository repository;

	@Override
	public Long register(BoardDto dto) {
		Board board = dtoToEntity(dto);
		repository.save(board);
		
		return board.getBno();
	}

	@Override
	public PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto) {
		//게시글, 회원, 댓글의 수
		Function<Object[], BoardDto> fn = (en -> entityToDto((Board)en[0], 
				(Member)en[1], (Long)en[2]));
		
		//Pageable pageable = pageRequestDto.getPageable(Sort.by("bno").descending());
		
		//Page<Object[]> result = repository.getBoardWithReplycount(pageable);
		
		Page<Object[]> result = repository.searchPage(
				pageRequestDto.getType(), 
				pageRequestDto.getKeyword(), 
				pageRequestDto.getPageable(Sort.by("bno").descending()) );
		
		return new PageResultDto<>(result, fn);

	}

	@Override
	public BoardDto get(Long bno) {
		Object result = repository.getBoardByBno(bno);
		Object[] arr = (Object[]) result;

		return entityToDto((Board)arr[0], (Member)arr[1], (Long)arr[2]);
	}
}
