package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.shop.dto.OrderDto;
import com.shop.dto.OrderHistDto;
import com.shop.dto.OrderItemDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.entity.Member;
import com.shop.entity.OrderItem;
import com.shop.entity.Orders;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {
	
	private final ItemRepository itemRepo;

    private final MemberRepository memberRepo;

    private final OrderRepository orderRepo;
    
    private final ItemImgRepository itemImgRepo;
    
    //주문하기(바로 구매)
    public Long order(OrderDto orderDto, String email){
    	//주문할 상품 조회
        Item item = itemRepo.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        //로그인한 회원 정보 조회
        Member member = memberRepo.findByEmail(email);

        //회원 정보와 주문할 상품 리스트 정보를 이용하여 주문 엔티티를 생성
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);
        Orders order = Orders.createOrder(member, orderItemList);
        orderRepo.save(order);  //주문 엔티티 저장

        return order.getId();
    }
    
    //구매 이력 - 주문 목록 조회
    @Transactional(readOnly = true)
    public Page<OrderHistDto> getOrderList(String email, Pageable pageable) {

        List<Orders> orders = orderRepo.findOrders(email, pageable); //주문 조회
        Long totalCount = orderRepo.countOrder(email);  //주문 총개수

        //주문 리스트를 순회하면서 구매 이력 페이지에 전달할 DTO를 생성함
        List<OrderHistDto> orderHistDtos = new ArrayList<>();

        for (Orders order : orders) {
            OrderHistDto orderHistDto = new OrderHistDto(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
            	//대표 이미지 조회
                ItemImg itemImg = 
                		itemImgRepo.findByItemIdAndRepimgYn(orderItem.getItem().getId(), "Y");
                
                OrderItemDto orderItemDto =
                        new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistDto.addOrderItemDto(orderItemDto);
            }

            orderHistDtos.add(orderHistDto);
        }

        //페이지 구현 객체를 생성하여 반환함
        return new PageImpl<OrderHistDto>(orderHistDtos, pageable, totalCount);
    }
    
    //현재 로그인한 사용자와 주문 데이터를 생성한 사용자가 같은지 검사
    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email){
        Member curMember = memberRepo.findByEmail(email);
        Orders order = orderRepo.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = order.getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }

        return true;
    }

    //주문 취소
    public void cancelOrder(Long orderId){
        Orders order = orderRepo.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    }
    
    //장바구니에서 주문할 상품 데이터를 받아서 주문하기
    public Long orders(List<OrderDto> orderDtoList, String email) {
    	Member member = memberRepo.findByEmail(email);
    	List<OrderItem> orderItemList = new ArrayList<>();
    	
    	for(OrderDto orderDto : orderDtoList) {
    		Item item = itemRepo.findById(orderDto.getItemId())
    				.orElseThrow(EntityNotFoundException::new);
    		OrderItem orderItem = 
    				OrderItem.createOrderItem(item, orderDto.getCount());
    		orderItemList.add(orderItem);
    	}
    	
    	//주문 객체 생성
    	Orders order = Orders.createOrder(member, orderItemList);
    	orderRepo.save(order); //주문 데이터 저장
    	
    	return order.getId();
    }
    
}
