package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.domain.Member;
import com.boot.service.MemberService;

//Model 객체- 'member'를 자동으로 세션에 저장함
@SessionAttributes("member")  
@Controller
public class LoginController {
	
	@Autowired
	private PasswordEncoder pwencoder;
	
	@Autowired
	private MemberService service;
	
	//로그인 폼 페이지 요청
	@GetMapping("/login")
	public void loginView() {
	}
	
	//로그인 인증 처리
	@PostMapping("/login")
	public String login(Member member, Model model){
		Member findMember = service.getMember(member);
		//암호화된 비밀번호 비교 - matches(입력 비밀번호, 암호화된 비밀번호)
		if(findMember != null &&
				pwencoder.matches(member.getPassword(), findMember.getPassword())) {
			model.addAttribute("member", findMember);  //세션 발급
			return "redirect:getBoardList";
		}else{
			int error = 1;
			model.addAttribute("error", error);
			return "login";
		}
		
		/*if(findMember != null 
				&& findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember);  //세션 발급
			return "redirect:getBoardList";
		}else {
			return "redirect:login";
		}*/
	}
	
	//@SessionAttributes를 사용한 경우 SessionStatus 클래스로 
	//로그아웃 처리
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:";
	}
	
	/*@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:"; //경로가 공백이면 '/' 경로와 같음
	}*/
	
	//회원 가입 폼 페이지 요청
	@GetMapping("/signup")
	public String signupView() {
		return "signup";
	}
	
	//회원 가입 처리
	@PostMapping("/signup")
	public String signup(Member member) {
		service.signup(member);
		return "redirect:";
	}
	
	//ID 중복 체크
	@GetMapping("/checkID")
	@ResponseBody  //데이터 전송 어노테이션
	public int checkID(String id) {
		int result = service.checkID(id);
		return result;
	}
	
	//회원 상세 정보
	@GetMapping("/memberView")
	public String memberView(String id, Model model) {
		Member member = service.getMember(id);
		model.addAttribute("member", member);
		return "memberView";
	}
	
	//회원 탈퇴
	@GetMapping("/deleteMember")
	public String delete(Member member) {
		service.delete(member);
		return "redirect:";
	}
	
	//회원 수정
	@PostMapping("/updateMember")
	public String update(Member member) {
		service.update(member);
		return "redirect:";
	}
}












