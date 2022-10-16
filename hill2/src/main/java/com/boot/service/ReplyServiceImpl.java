package com.boot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.boot.dto.ReplyDto;
import com.boot.entity.Board;
import com.boot.entity.Reply;
import com.boot.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService{
	
	private final ReplyRepository replyRepo;

	//댓글 목록
	@Override
	public List<ReplyDto> getList(Long bno) {
		
		Board board = Board.builder().bno(bno).build();
		
		List<Reply> result = replyRepo.getRepliesByBoardOrderByRno(board);
		
		return result.stream().map(reply -> entityToDto(reply))
				.collect(Collectors.toList());
	}

	//댓글 쓰기
	@Override
	public Long register(ReplyDto replyDto) {
		Reply reply = dtoToEntity(replyDto);
		replyRepo.save(reply);
		return reply.getRno();
	}

	//댓글 삭제
	@Override
	public void remove(Long rno) {
		replyRepo.deleteById(rno);
	}

	//댓글 수정
	@Override
	public void modify(ReplyDto replyDto) {
		Reply reply = dtoToEntity(replyDto);
		replyRepo.save(reply);
	}

}
