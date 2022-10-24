package com.shop.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;

import lombok.Getter;
import lombok.Setter;

//상품 데이터 정보를 전달하는 DTO
@Getter @Setter
public class ItemFormDto {
	
	private Long id;
	
	@NotBlank(message = "상품명은 필수 입력 값입니다.")
	private String itemNm;
	
	@NotNull(message = "가격은 필수 입력 값입니다.")
	private Integer price;
	
	@NotBlank(message = "상품 설명은 필수 입력 값입니다.")
	private String itemDetail;
	
	@NotNull(message = "재고는 필수 입력 값입니다.")
	private Integer stockNumber;
	
	private ItemSellStatus itemSellStatus;
	
	//상품 이미지 정보 저장 리스트
	private List<ItemImgDto> itemImgDtoList = new ArrayList<>(); 
	
	//이미지 아이디 저장 리스트(등록시엔 사용하지 않고 수정시에 사용됨)
	private List<Long> itemImgIds = new ArrayList<>(); 
	
	//ItemFormDto를 Item Entity로 반환하는 모듈
	private static ModelMapper modelMapper = new ModelMapper();
	
	//modelMapper를 이용하여 엔티티의 객체와 DTO 객체 간의 데이터를 복사하여 
	//복사한 객체를 반환하는 메서드
	public Item createItem() {
		return modelMapper.map(this, Item.class);
	}
	
	public static ItemFormDto of(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
	}
}








