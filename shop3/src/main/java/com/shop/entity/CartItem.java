package com.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.shop.config.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/* 다대일 단방향 매핑
   하나의 장바구니에는 여러 개의 상품이 들어갈 수 있음(다대일 관계)
   하나의 상품은 여러 장바구니의 장바구니 상품으로 담길 수 있으므로 (다대일 관계)
   또한 같은 상품을 여러 개 주문할 수도 있어서 몇 개를 담아 줄 것인지 설정해야 함 */

@Getter @Setter
@Entity
public class CartItem extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name="cart_item_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	private int count;
}








