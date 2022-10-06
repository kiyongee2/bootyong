package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("/list")
	public String list(PageRequestDto pageRequestDto, Model model) {
		log.info("list............." +  pageRequestDto);
	
		//model.addAttribute("result", service.getList(pageRequestDto));
		
		return "guestbook/list";
	}
	
}
