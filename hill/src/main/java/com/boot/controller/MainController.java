package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	//index 페이지
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	//관리자 페이지
	@GetMapping("/admin/adminPage")
	public void adminPage() {}
	
	//접근 권한 없음 페이지
	@GetMapping("/admin/accessDenied")
	public void accessDenied() {}
}
