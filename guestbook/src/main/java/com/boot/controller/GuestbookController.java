package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.GuestbookDto;
import com.boot.dto.PageRequestDto;
import com.boot.service.GuestbookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/guestbook")
@Controller
public class GuestbookController {
	
	private final GuestbookService service;
	
	/*@GetMapping("/list")
	public String list() {
		log.info("list.............");
		
		return "guestbook/list";
	}*/
	
	@GetMapping("/list")
	public String list(PageRequestDto pageRequestDto, Model model) {
		log.info("list............." +  pageRequestDto);
		
		model.addAttribute("result", service.getList(pageRequestDto));
		
		return "guestbook/list";
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("register get....");
	}
	
	@PostMapping("/register")
	public String register(GuestbookDto dto, RedirectAttributes 
			redirectAttributes) {
		Long gno = service.register(dto);
		redirectAttributes.addFlashAttribute("msg", gno);
		return "redirect:list";
	}
	
	@GetMapping("/read")
	public void read(Long gno, @ModelAttribute("requestDto") PageRequestDto
			requestDto, Model model) {
		log.info("gno: " + gno);
		GuestbookDto dto = service.read(gno);
		
		model.addAttribute("dto", dto);
	}
}







