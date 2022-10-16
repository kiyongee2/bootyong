package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/admin/adminPage")
	public void adminPage() {}
}
