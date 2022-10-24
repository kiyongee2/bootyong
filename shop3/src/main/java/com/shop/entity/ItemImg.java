package com.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.shop.config.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class ItemImg extends BaseEntity{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "item_img_id")
	private Long id;
	
	private String imgName;  //이미지 파일명
	
	private String oriImgName;  //원본 이미지 파일명
	
	private String imgUrl;  //이미지 조회 경로
	
	private String repimgYn;  //대표 이미지 여부
	
	//상품 엔티티와 다대일 관계 설정. 지연로딩
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	//이미지 정보를 변경하는 메서드
	public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
		this.oriImgName = oriImgName;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
	}
}
