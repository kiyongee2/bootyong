package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board/")
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/list")
	public void list(PageRequestDto pageRequestDto, Model model) {
		model.addAttribute("result", boardService.getList(pageRequestDto));
	}
	
	//글 상세 보기
	@GetMapping("/read")
	public void register(Long bno, @ModelAttribute("requestDto") PageRequestDto
			requestDto, Model model) {
		BoardDto boardDto = boardService.get(bno);
		
		model.addAttribute("dto", boardDto);
	}
	
	//글쓰기 폼 요청
	@GetMapping("/register")
	public void register() {}
	
	//글쓰기 처리
	@PostMapping("/register")
	public String register(BoardDto dto, RedirectAttributes
			redirectAttributes) {
		Long bno = boardService.register(dto);
		redirectAttributes.addFlashAttribute("msg", bno);
		return "redirect:list";
	}

}
