package com.boot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.dto.ItemDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/sample")
@Controller
public class SampleController {
	
	@GetMapping("/ex1")
	public void ex1() {
		log.info("ex1..........");
	}
	
	//상품 1개 정보
	@GetMapping("/ex2")
	public String ex2(Model model) {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemNm("상품1");
		itemDto.setPrice(20000);
		itemDto.setItemDetail("상품1 상세 설명");
		itemDto.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemDto", itemDto);
		return "sample/ex2";
	}
	
	//상품 목록
	@GetMapping("/ex3")
	public String ex3(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		
		for(int i=1; i<=10; i++) {
			ItemDto itemDto = new ItemDto();
			itemDto.setId((long) i);
			itemDto.setItemNm("상품" + i);
			itemDto.setPrice(1000*i);
			itemDto.setItemDetail("상품" + i + "상세 설명");
			itemDto.setRegTime(LocalDateTime.now());
			
			itemDtoList.add(itemDto);
		}
		model.addAttribute("itemDtoList", itemDtoList);
		return "sample/ex3";
	}
	
	@GetMapping("/ex4")
	public String ex4() {
		return "sample/ex4";
	}
	
	@GetMapping("/ex5")
	public String ex5() {
		return "sample/ex5";
	}
	
}
