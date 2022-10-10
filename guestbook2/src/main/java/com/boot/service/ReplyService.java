package com.boot.service;

import java.util.List;

import com.boot.dto.ReplyDto;
import com.boot.entity.Board;
import com.boot.entity.Reply;

public interface ReplyService {
	
	Long register(ReplyDto replyDTO);  //댓글 등록
	
	List<ReplyDto> getList(Long bno);  //특정 게시글의 댓글 목록
	
	void modify(ReplyDto replyDto);  //댓글 수정
	
	void remove(Long rno); //댓글 삭제
	
	//ReplyDto를 Reply 객체로 변환 Board 객체의 처리가 수반됨
	default Reply dtoToEntity(ReplyDto replyDto) {
		
		Board board = Board.builder().bno(replyDto.getBno()).build();
		
		Reply reply = Reply.builder()
				.rno(replyDto.getRno())
				.text(replyDto.getText())
				.replyer(replyDto.getReplyer())
				.board(board)
				.build();
		
		return reply;		
	}
	
	//Reply 객체를 ReplyDto로 변환
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
}









