package com.shop.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.shop.config.BaseEntity;
import com.shop.constant.ItemSellStatus;
//import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemFormDto;
import com.shop.exception.OutOfStockException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Table(name="item_id")
@Getter
@Setter
@ToString
@Entity
public class Item extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="item_id")
	private Long id;        //상품 코드
	
	@Column(nullable=false, length=50)
	private String itemNm;  //상품명
	
	@Column(name="price", nullable=false)
	private int price;  //가격
	
	@Column(nullable=false)
	private int stockNumber;  //재고 수량
	
	@Lob
	@Column(nullable=false)
	private String itemDetail; //상품 상세 설명
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus; //상품 판매 상태
	
	//상품 수정 메서드
	public void updateItem(ItemFormDto itemFormDto) {
		this.itemNm = itemFormDto.getItemNm();
		this.price = itemFormDto.getPrice();
		this.stockNumber = itemFormDto.getStockNumber();
		this.itemDetail = itemFormDto.getItemDetail();
		this.itemSellStatus = itemFormDto.getItemSellStatus();
	}
	
	//재고를 감소시키는 메서드
	public void removeStock(int stockNumber) {
		int restStock = this.stockNumber - stockNumber;
		if(restStock < 0) {
			throw new OutOfStockException("상품의 재고가 부족합니다. "
					+ "(현재 재고 수량: " + this.stockNumber + ")");
		}
		this.stockNumber = restStock;
	}
	
	//재고 증가
	public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }
}
