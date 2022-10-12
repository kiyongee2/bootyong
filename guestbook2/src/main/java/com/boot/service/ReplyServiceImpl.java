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
	
	private final ReplyRepository repository;

	//댓글 등록
	@Override
	public Long register(ReplyDto replyDTO) {
		Reply reply = dtoToEntity(replyDTO);
		repository.save(reply);
		
		return reply.getRno();
	}

	//댓글 목록 보기
	@Override
	public List<ReplyDto> getList(Long bno) {
		List<Reply> result = repository.
				getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());
		return result.stream().map(reply -> entityToDto(reply))
				.collect(Collectors.toList());
	}

	//댓글 수정
	@Override
	public void modify(ReplyDto replyDto) {
		Reply reply = dtoToEntity(replyDto);
		repository.save(reply);
	}

	//댓글 삭제
	@Override
	public void remove(Long rno) {
		repository.deleteById(rno);
	}

}
