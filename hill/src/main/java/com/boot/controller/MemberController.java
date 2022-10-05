package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.domain.Member;
import com.boot.service.MemberService;

@RequestMapping("/member/")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public void login() {}
	
	@GetMapping("/logout")
	public void logout() {}
	
	@GetMapping("/signup")
	public void signup() {}
	
	//회원 가입 처리
	@PostMapping("/signup")
	public String signup(Member member) {
		memberService.signup(member);
		return "redirect:/";
	}
	
}
