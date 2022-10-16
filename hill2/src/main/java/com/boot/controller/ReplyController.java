package com.boot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.dto.ReplyDto;
import com.boot.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/replies/")
@RestController
public class ReplyController {
	
	private final ReplyService replyService;
	
	//댓글 목록
	//@PathVariable - 경로를 변수로 처리해줌
	@GetMapping(value = "/board/{bno}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDto>> getListByBoard(
			@PathVariable("bno") Long bno){
		List<ReplyDto> replyDto = replyService.getList(bno);
		return new ResponseEntity<>(replyDto, HttpStatus.OK);
	}
	
	//댓글 쓰기
	//@RequestBody : 데이터를 자동으로 해당 타입의 객체로 매핑함
	@PostMapping("")
	public ResponseEntity<Long> register(@RequestBody ReplyDto replyDto){
		Long rno = replyService.register(replyDto);
		//http 연결에 성공하면 rno를 보내줌
		return new ResponseEntity<>(rno, HttpStatus.OK);
	}
	
	//댓글 삭제
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		replyService.remove(rno);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	//댓글 수정
	@PutMapping("/{rno}")
	public ResponseEntity<String> modify(@RequestBody ReplyDto replyDto){
		replyService.modify(replyDto);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
