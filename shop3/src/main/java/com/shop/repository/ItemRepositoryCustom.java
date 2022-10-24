package com.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
//import com.shop.dto.MainItemDto;
import com.shop.entity.Item;

//사용자 정의 인터페이스 작성
public interface ItemRepositoryCustom {
	
	//검색과 페이지 처리 리스트
	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
	
	//메인 페이지에서 보여줄 상품 리스트
	Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto,
			Pageable pageable);
}
