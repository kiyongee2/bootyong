package com.boot.service;

import java.util.List;

import com.boot.dto.ReplyDto;
import com.boot.entity.Board;
import com.boot.entity.Reply;

public interface ReplyService {
	
	//댓글 목록 보기
	List<ReplyDto> getList(Long bno);
	
	//댓글 등록
	Long register(ReplyDto replyDto);
	
	//댓글 삭제
	void remove(Long rno);
	
	//댓글 수정
	void modify(ReplyDto replyDto);
	
	//Reply 객체(엔티티)를 ReplyDto로 반환
	default ReplyDto entityToDto(Reply reply) {
		ReplyDto dto = ReplyDto.builder()
				.rno(reply.getRno())
				.text(reply.getText())
				.replyer(reply.getReplyer())
				.regDate(reply.getRegDate())
				.modDate(reply.getModDate())
				.build();
		
		return dto;
	}
	
	//ReplyDto를 Reply 객체로 반환
	default Reply dtoToEntity(ReplyDto replyDto) {
		//게시글 1개 생성
		Board board = Board.builder().bno(replyDto.getBno()).build();
		
		//댓글 작성
		Reply reply = Reply.builder()
				.rno(replyDto.getRno())
				.text(replyDto.getText())
				.replyer(replyDto.getReplyer())
				.board(board)
				.build();
		return reply;
	}
}






