package com.shop.entity;

import javax.persistence.*;

import com.shop.config.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class OrderItem extends BaseEntity{
	@Id @GeneratedValue
	@Column(name="order_item_id")
	private Long id;
	
	//하나의 상품은 여러 주문 상품으로 들어갈 수 있음. 주문 상품과 상품이 다대일 관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	//한 번에 여러개의 상품 주문. 주문 상품과 주문이 다대일 관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="orders_id")
	private Orders orders;
	
	private int orderPrice;  //주문 가격
	
	private int count;       //주문 수량
	
	//주문할 상품과 수량으로 OrderItem 객체 생성
	public static OrderItem createOrderItem(Item item, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setCount(count);
		orderItem.setOrderPrice(item.getPrice());  //상품 가격을 주문 가격으로 세팅
		
		item.removeStock(count); //주문 수량 만큼 상품의 재고 수량 감소시킴
		return orderItem;
	}
	
	//주문 총 가격
	public int getTotalPrice() {
		return orderPrice * count;
	}
	
	//주문 취소시 주문 수량 만큼 재고를 더해줌
	public void cancel() {
        this.getItem().addStock(count);
    }
}
