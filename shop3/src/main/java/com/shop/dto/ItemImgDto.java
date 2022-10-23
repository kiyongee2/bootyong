package com.shop.dto;

import org.modelmapper.ModelMapper;

import com.shop.entity.ItemImg;

import lombok.Getter;
import lombok.Setter;

//상품 이미지에 대한 데이터를 전달할 DTO
@Getter @Setter
public class ItemImgDto {
	
	private Long id;         //자동 순번
	
	private String imgName;  //이미지 파일명
	
	private String oriImgName;  //원본 이미지 파일명
	
	private String imgUrl;  //이미지 조회 경로
	
	private String repimgYn;  //대표 이미지 여부
	
	//ModelMapper (entity와 dto를 상호 변환하는 모듈)
	private static ModelMapper modelMapper = new ModelMapper();
	
	//ItemImg 객체 자료형과 멤버변수의 이름이 같을 때 ItemImgDto로 값을 복사해서 반환함
	public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
