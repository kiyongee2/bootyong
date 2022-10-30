package com.shop.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.shop.constant.OrderStatus;
import com.shop.entity.Orders;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderHistDto {
	
	private Long orderId; //주문아이디
	
    private String orderDate; //주문날짜
    
    private OrderStatus orderStatus; //주문 상태

    //주문 상품리스트
    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    //order 객체를 파라미터로 받아서 필드 세팅 - 생성자
	public OrderHistDto(Orders order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    //orderItemDto를 주문 상품 리스트에 추가
    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }	
}
