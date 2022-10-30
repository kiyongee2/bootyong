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
public class CartItem extends BaseEntity{
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cart_item_id")
	private Long id;
	
	//같은 상품을 여러 개 주문할 수도 있어서 몇 개를 담아 줄 것인지 설정함
	private int count;
	
	//하나의 상품은 여러 장바구니의 장바구니 상품으로 담길 수 있으므로 (다대일 관계)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	//하나의 장바구니에는 여러 개의 상품이 들어갈 수 있음(다대일 관계)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	//장바구니에 담을 상품 엔티티 생성 메서드
	public static CartItem createCartItem(Cart cart, Item item, int count) {
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		cartItem.setItem(item);
		cartItem.setCount(count);
		return cartItem;
	}
	
	//해당 상품을 추가로 장바구니에 담을 메서드
	public void addCount(int count) {
		this.count += count;
	}
	
	//장바구니 품목 수량 변경
	public void updateCount(int count) {
		this.count = count;
	}
	
	
}












