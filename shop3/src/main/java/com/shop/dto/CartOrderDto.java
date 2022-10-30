package com.shop.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartOrderDto {
	
	//장바구니 품목 아이디
	private Long cartItemId;
	
	//장바구니 품목 리스트
	private List<CartOrderDto> cartOrderDtoList;
}
