package com.boot.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.config.SecurityUser;
import com.boot.domain.Board;
import com.boot.dto.FileDto;
import com.boot.service.BoardService;

@RequestMapping("/board/")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//글 목록 보기
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";
	}
	
	//글 상세 보기
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		boardService.updateCount(seq);  //조회수
		Board board = boardService.getBoard(seq);
		model.addAttribute("board", board);
		return "board/getBoard";
	}
	
	//글 쓰기 폼 요청
	@GetMapping("/insertBoard")
	public void insertBoard() {}
	
	//글 쓰기 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @RequestParam MultipartFile[] uploadFile,
			@AuthenticationPrincipal SecurityUser principal) throws IllegalStateException, IOException {
		//파일 업로드
		List<FileDto> list = new ArrayList<>();
		for(MultipartFile file : uploadFile) {
			if(!file.isEmpty()) {
				FileDto dto = new FileDto(UUID.randomUUID().toString(),
						file.getOriginalFilename(), file.getContentType());
				list.add(dto);
				
				File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
				//전달된 내용을 실제 물리적인 파일로 저장해 줌
				file.transferTo(newFileName);	
			}
		}
		
		//글쓰기
		board.setMember(principal.getMember());
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 수정 처리
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 삭제 처리
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "redirect:getBoardList";
	}
}
