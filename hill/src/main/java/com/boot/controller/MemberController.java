package com.boot.controller;

import java.security.Principal;
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
import com.boot.dto.MemberInput;
import com.boot.dto.ServiceResult;
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
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
	
	//회원 가입 폼 요청
	@GetMapping("/signup")
	public void signup() {}
	
	//회원 가입 처리
	@PostMapping("/signup")
	public String signup(Member member, Model model) {
		memberService.signup(member);
		model.addAttribute("가입", "회원 가입을 축하합니다.");
		return "member/result";
	}
	
	//회원 상세 정보
	/*@GetMapping("/view")
	public String view(String userid, Model model,
			@AuthenticationPrincipal SecurityUser principal) {
		//board.setMember(principal.getMember());
		Member member = memberService.view(userid);
		model.addAttribute("member", member);
		return "member/view";
	}*/
	
	@GetMapping("/view")
	public String view(String userid, Model model, 
			@AuthenticationPrincipal SecurityUser principal) {
		Member member = memberService.view(userid);
		model.addAttribute("member", member);
		return "member/view";
	}
	
	//비밀번호 변경 폼 요청
	@GetMapping("/password")
	public String updatePassword(Model model, @AuthenticationPrincipal SecurityUser principal) { 
		String userid = principal.getUsername();
		Member member = memberService.view(userid);
		model.addAttribute("member", member);
		return "member/password";
	}
	
	//비밀번호 변경 처리
	@PostMapping("/password")
	public String updatePassword(MemberInput parameter, 
			@AuthenticationPrincipal SecurityUser principal,
			Model model) {
		String userid = principal.getUsername();
		parameter.setUserid(userid);
		ServiceResult result = memberService.updatePassword(parameter);
		if(!result.isResult()) {
			return "member/password";
		}
		
		return "redirect:view";
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
