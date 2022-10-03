package com.boot.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDto {
	
	private Long id;         //자동 순번
	
	private String itemNm;   //상품 이름
	
	private Integer price;   //상품 가격
	
	private String itemDetail; //상품 상세 설명
	
	private LocalDateTime regTime; //상품 등록일
}
