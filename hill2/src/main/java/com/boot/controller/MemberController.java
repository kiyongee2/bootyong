package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.entity.Member;
import com.boot.service.MemberService;

@RequestMapping("/member/")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//로그인
	@GetMapping("/login")
	public void login(String error, Model model) {
		if(error != null) {
			model.addAttribute("error", "아이디나 비밀번호를 확인해주세요");
		}
		
	}  //templates/member/login.html
	
	//로그아웃
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
	
	//회원 가입 폼 요청
	@GetMapping("/signup")
	public void signup() {}  //member/signup.html
	
	//회원 가입 처리
	@PostMapping("/signup")
	public String signup(Member member, Model model) {
		memberService.signup(member);
		model.addAttribute("msg", "가입");
		return "member/result";
	}
	
	//회원 정보 보기
	@GetMapping("/view")
	public String view(String userid, Model model) {
		Member member = memberService.view(userid);
		model.addAttribute("member", member);
		return "member/view";  //member/view.html
	}
	
	//회원 정보 수정
	@PostMapping("/update")
	public String update(Member member, Model model) {
		memberService.update(member);
		model.addAttribute("msg", "수정");
		return "member/result";
	}
	
	@GetMapping("/delete")
	public String delete(Member member, Model model) {
		memberService.delete(member);
		model.addAttribute("msg", "삭제");
		return "member/result";
	}
}






