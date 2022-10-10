package com.boot.service;

import com.boot.dto.GuestbookDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Guestbook;

public interface GuestbookService {
	
	Long register(GuestbookDto dto);  //게시글 등록
	
	//게시글 목록 보기
	PageResultDto<GuestbookDto, Guestbook> getList(PageRequestDto requestDto);
	
	//게시글 상세 보기
	GuestbookDto read(Long gno);
	
	//dto를 Entity로 변환
	default Guestbook dtoToEntity(GuestbookDto dto) {
		Guestbook entity = Guestbook.builder()
				.gno(dto.getGno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();
		return entity;
	}
	
	//Entity를 Dto로 변환
	default GuestbookDto entityToDto(Guestbook entity) {
		GuestbookDto dto = GuestbookDto.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
		return dto;
	}
}
