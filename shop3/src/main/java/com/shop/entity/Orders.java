package com.shop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.shop.config.BaseEntity;
import com.shop.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;

//주의!! Order로 하면 에러남 (Order가 정렬 예약어 이므로..)
@Getter @Setter
@Entity
public class Orders extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name="orders_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;           //회원 엔티티 
	
	private LocalDateTime orderDate;  //주문일
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;  //주문 상태
	
	//양방향 연관 매핑 주인 설정(OrderItem이 주인임)
	//영속성 전이(cascade) - Order가 삭제되면 OrderItem도 함께 삭제됨
	@OneToMany(mappedBy="orders", cascade=CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	//주문 상품 추가
	public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrders(this);  //Orders를 orderItem에 세팅
    }

	//주문 추가
    public static Orders createOrder(Member member, List<OrderItem> orderItemList) {
        Orders order = new Orders();
        order.setMember(member);

        for(OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }

        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //총 주문 금액 계산
    public int getTotalPrice() {
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    //주문 상태를 취소 상태로 바꿈
    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }


}
