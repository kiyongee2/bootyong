package com.boot.service;

import java.util.List;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Board;
import com.boot.entity.Member;

public interface BoardService {
	
	//게시글 쓰기
	Long register(BoardDto dto);
	
	//게시글 목록 보기
	PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto);
	
	//게시글 상세 보기
	BoardDto get(Long bno);
	
	//게시글 삭제
	void remove(Long bno);
	
	//게시글 수정
	void modify(BoardDto dto);
	
	//조회수 
	void updateCount(Long bno);
	
	//dto에서 Entity로 변환
	default Board dtoToEntity(BoardDto dto) {
		//회원 생성
		Member member = Member.builder().userid(dto.getWriterUserid()).build();
		
		//게시글 작성
		Board board = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.cnt(dto.getCnt())
				.build();
		return board;
	}
	
	//Entity에서 dto로 변환
	default BoardDto entityToDto(Board board, Member member, Long replyCount) {
		
		BoardDto boardDto = BoardDto.builder()
				.bno(board.getBno())
				.title(board.getTitle())
				.content(board.getContent())
				.cnt(board.getCnt())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
				.writerUserid(member.getUserid())
				.writerName(member.getName())
				.replyCount(replyCount.intValue())  //int로 변환
				.build();
		return boardDto;		
	}
}
