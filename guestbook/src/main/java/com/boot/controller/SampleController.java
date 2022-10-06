package com.boot.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.domain.FileDto;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/sample/")
@Controller
public class SampleController {
	
	/*@GetMapping("/exLayout1")
	public void exLayout1() {
		log.info("exLayout1.....");
	}*/
	
	//타임리프 Layout
	@GetMapping({"/exLayout1", "/exTemplate", "/exBasic"})
	public void exLayout() {
		log.info("exLayout........");
	}
	
	//파일 업로드 폼 요청
	@GetMapping("/upload")
	public void upload() {}
	
	//파일 업로드
	@PostMapping("/upload")
	public String upload(@RequestParam MultipartFile[] uploadfile, Model model) throws IllegalStateException, IOException {
		List<FileDto> list = new ArrayList<>();
		for(MultipartFile file : uploadfile) {
			if(!file.isEmpty()) {
				FileDto dto = new FileDto(UUID.randomUUID().toString(),
						file.getOriginalFilename(), file.getContentType());
				list.add(dto);
				
				File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
				//전달된 내용을 실제 물리적인 파일로 저장해 줌
				file.transferTo(newFileName);	
			}
		}
		model.addAttribute("files", list);
		return "sample/result";
	}
}









