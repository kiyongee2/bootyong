package com.boot.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.dto.GuestbookDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Guestbook;
import com.boot.entity.QGuestbook;
import com.boot.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class GuestBookServiceImpl implements GuestbookService{
	
	private final GuestbookRepository repository;
	
	//글등록
	@Override
	public Long register(GuestbookDto dto) {
		log.info("DTO....");
		log.info(dto);
		
		Guestbook entity = dtoToEntity(dto);
		log.info(entity);
		
		repository.save(entity);
	
		return entity.getGno();
	}

	//글 목록보기
	@Override
	public PageResultDto<GuestbookDto, Guestbook> getList(PageRequestDto requestDto) {
		
		Pageable pageable = requestDto.getPageable(Sort.by("gno").descending()); //페이징 
		
		BooleanBuilder booleanBuilder = getSearch(requestDto);   //검색 처리
		
		Page<Guestbook> result = repository.findAll(booleanBuilder, pageable);
		
		Function<Guestbook, GuestbookDto> fn = (entity -> entityToDto(entity));
		
		return new PageResultDto<>(result, fn);
	}

	//글 상세보기
	@Override
	public GuestbookDto read(Long gno) {
		Optional<Guestbook> result = repository.findById(gno);
		return result.isPresent() ? entityToDto(result.get()) : null;
	}
	
	//검색
	private BooleanBuilder getSearch(PageRequestDto requestDto) {
		String type = requestDto.getType();
		String keyword = requestDto.getKeyword();
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		BooleanExpression expression = qGuestbook.gno.gt(0L);  //gno > 0
		booleanBuilder.and(expression);
		
		//검색 조건이 없는 경우 - null 처리
		if(type == null || type.trim().length() == 0){
			return booleanBuilder;
		}
		
		//검색 조건 작성
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		
		if(type.contains("t")) {
			conditionBuilder.or(qGuestbook.title.contains(keyword));
		}
		
		if(type.contains("c")) {
			conditionBuilder.or(qGuestbook.content.contains(keyword));
		}
		
		if(type.contains("w")) {
			conditionBuilder.or(qGuestbook.writer.contains(keyword));
		}
		
		//모든 조건 통합
		booleanBuilder.and(conditionBuilder);
		
		return booleanBuilder;
	}
}






