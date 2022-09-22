package com.boot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.boot.domain.Board;
import com.boot.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	//게시글 목록 
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = service.getBoardList();

		model.addAttribute("boardList", boardList);
			
		return "getBoardList";
	}
	
	//게시글 등록 폼 요청
	@GetMapping("/insertBoard")
	public String insertBoard() {
		return "insertBoard";
	}
	
	//게시글 등록 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		service.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	//게시글 상세 조회
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		Board board = service.getBoard(seq);
		model.addAttribute(board);
		return "getBoard";
	}
	
	//게시글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		return "redirect:getBoardList";
	}
	
	//게시글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	//화면 개발 예제
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}









