package com.boot.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.config.SecurityUser;
import com.boot.dto.BoardDto;
import com.boot.dto.FileDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Board;
import com.boot.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board/")
@Controller
public class BoardController {
	
	private final BoardService boardService;

	//게시글 목록 보기
	@GetMapping("/list")
	public String list(PageRequestDto pageRequestDto, Model model) {
		PageResultDto<BoardDto, Object[]> result = boardService.getList(pageRequestDto);
		model.addAttribute("result", result);
		return "board/list";
	}
	
	//글쓰기 폼 페이지 요청
	@GetMapping("/register")
	public void register() {}
	
	//글쓰기 처리
	@Transactional
	@PostMapping("/register")
	public String register(BoardDto boardDto, Board board,
			@AuthenticationPrincipal SecurityUser principal,
			@RequestParam MultipartFile[] uploadFile,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		//파일 업로드
		//MultipartFile[]를 파라미터로 객체 사용
		for(MultipartFile file : uploadFile) {
			if(!file.isEmpty()) {
				//FileDto 객체 생성
				FileDto dto = new FileDto(UUID.randomUUID().toString(),
						file.getOriginalFilename(), file.getContentType());
				
				//파일 생성 - File 클래스의 객체는 논리적인 파일 이름임
				File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
				//실제 물리적인 파일로 전달해서 저장
				file.transferTo(newFileName);
			}
		}
		//글쓰기
		boardDto.setWriterUserid(principal.getUsername());
		Long bno = boardService.register(boardDto);
		redirectAttributes.addFlashAttribute("msg", bno);
		return "redirect:list";
	}
	
	//게시글 상세 보기
	@GetMapping("/read")
	public void read(@ModelAttribute("requestDto") PageRequestDto requestDto
			, Long bno, Model model) {
		//조회수 증가
		boardService.updateCount(bno);
		//글 상세 보기
		BoardDto boardDto = boardService.get(bno);
		model.addAttribute("dto", boardDto);
	}
	
	//게시글 삭제
	@GetMapping("/delete")
	public String delete(Long bno) {
		boardService.remove(bno);
		return "redirect:list";
	}
	
	//게시글 수정
	@PostMapping("/update")
	public String update(BoardDto boardDto) {
		boardService.modify(boardDto);
		return "redirect:list";
	}
	
	//글쓰기 처리 요청
	/*@PostMapping("/insertBoard")
	public String insertBoard(Board board, @RequestParam MultipartFile[] uploadFile,
			@AuthenticationPrincipal SecurityUser principal) throws IllegalStateException, IOException {
		//파일 업로드
		//MultipartFile[]를 파라미터로 객체 사용
		for(MultipartFile file : uploadFile) {
			if(!file.isEmpty()) {
				//FileDto 객체 생성
				FileDto dto = new FileDto(UUID.randomUUID().toString(),
						file.getOriginalFilename(), file.getContentType());
				
				//파일 생성 - File 클래스의 객체는 논리적인 파일 이름임
				File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
				//실제 물리적인 파일로 전달해서 저장
				file.transferTo(newFileName);
			}
		}
		//글쓰기
		board.setWriter(principal.getMember()); //인증된 회원이 글쓰기 함
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}*/
}








