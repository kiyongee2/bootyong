package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.config.SecurityUser;
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
	public String logout() {
		return "redirect:/";
	}
	
	//회원 가입 폼 요청
	@GetMapping("/signup")
	public void signup() {}
	
	//회원 가입 처리
	@PostMapping("/signup")
	public String signup(Member member) {
		memberService.signup(member);
		return "redirect:login";
	}
	
	//회원 상세 정보
	@GetMapping("/view")
	public String view(String userid, Model model,
			@AuthenticationPrincipal SecurityUser principal) {
		//board.setMember(principal.getMember());
		Member member = memberService.view(userid);
		model.addAttribute("member", member);
		return "member/view";
	}
	
	//회원 수정
	@PostMapping("/update")
	public String update(Member member, Model model) {
		memberService.update(member);
		model.addAttribute("msg", "수정");
		return "member/result";
	}
	
	//회원 삭제
	@GetMapping("/delete")
	public String delete(Member member, Model model) {
		memberService.delete(member);
		model.addAttribute("msg", "삭제");
		return "member/result";
	}
}
