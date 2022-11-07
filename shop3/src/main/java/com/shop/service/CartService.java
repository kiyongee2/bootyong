package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.shop.dto.CartDetailDto;
import com.shop.dto.CartItemDto;
import com.shop.dto.CartOrderDto;
import com.shop.dto.OrderDto;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.repository.CartItemRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Transactional
@Service
public class CartService {
	
	private final ItemRepository itemRepo;
	private final MemberRepository memberRepo;
	private final CartRepository cartRepo;
	private final CartItemRepository cartItemRepo;
	private final OrderService orderService;
	
	//장바구니 생성(담기)
	public Long addCart(CartItemDto cartItemDto, String email) {
		//장바구니에 담을 상품 엔티티와 회원 조회
		Item item = itemRepo.findById(cartItemDto.getItemId())
				.orElseThrow(EntityNotFoundException::new);
		
		Member member = memberRepo.findByEmail(email);
		
		//현재 로그인한 회원의 장바구니 엔티티 조회
		Cart cart = cartRepo.findByMemberId(member.getId());
		
		//상품을 처음으로 장바구니에 담을 경우 해당 회원의 장바구니 엔티티를 생성
		if(cart == null) {
			cart = Cart.createCart(member);
			cartRepo.save(cart);
		}
		
		//카트 아이디와 상품 아이디를 이용하여 장바구니에 들어있는 상품을 조회함
		CartItem savedCartItem = 
				cartItemRepo.findByCartIdAndItemId(cart.getId(), item.getId());
		
		//장바구니에 있던 상품일 경우 기존 수량에 더하고, 아니면 장바구니에 들어갈 상품을 생성함
		if(savedCartItem != null) {
			savedCartItem.addCount(cartItemDto.getCount());
			return savedCartItem.getId();
		}else {
			CartItem cartItem = 
					CartItem.createCartItem(cart, item, cartItemDto.getCount());
			cartItemRepo.save(cartItem);
			return cartItem.getId();
		}
	}
	
	//장바구니 목록 조회
	public List<CartDetailDto> getCartList(String email){
		List<CartDetailDto> cartDetailDtoList = new ArrayList<>();
		
		Member member = memberRepo.findByEmail(email);
		Cart cart = cartRepo.findByMemberId(member.getId());
		if(cart == null) {
			return cartDetailDtoList;
		}
		
		cartDetailDtoList = cartItemRepo.findCartDetailDtoList(cart.getId());
		return cartDetailDtoList;
	}
	
	//현재 로그인한 사용자와 장바구니 상품 데이터를 생성한 사용자가 같은지 검사
    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email){
        Member curMember = memberRepo.findByEmail(email);
        CartItem cartItem = cartItemRepo.findById(cartItemId)
        		.orElseThrow(EntityNotFoundException::new);
        Member savedMember = cartItem.getCart().getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }

        return true;
    }
	
	//장바구니 상품 수량 업데이트
    public void updateCartItemCount(Long cartItemId, int count) {
    	CartItem cartItem = cartItemRepo.findById(cartItemId)
        		.orElseThrow(EntityNotFoundException::new);
    	
    	cartItem.updateCount(count);
    }
    
    //장바구니 상품 삭제
    public void deleteCartItem(Long cartItemId) {
    	CartItem cartItem = cartItemRepo.findById(cartItemId)
    			.orElseThrow(EntityNotFoundException::new);
    	cartItemRepo.delete(cartItem);
    }
    
    //장바구니 품목 주문하기
    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email) {
    	//주문 로직으로 전달할 orderDto 리스트 생성
    	List<OrderDto> orderDtoList = new ArrayList<>();
    	for(CartOrderDto cartOrderDto : cartOrderDtoList) {
    		CartItem cartItem = cartItemRepo.findById(cartOrderDto.getCartItemId())
    				.orElseThrow(EntityNotFoundException::new);
    		
    		OrderDto orderDto = new OrderDto();
    		orderDto.setItemId(cartItem.getItem().getId());
    		orderDto.setCount(cartItem.getCount());
    		orderDtoList.add(orderDto);
    	}
    	
    	Long orderId = orderService.orders(orderDtoList, email); //주문 로직 호출
    	
    	//주문후 장바구니 상품 삭제
    	/*for(CartOrderDto cartOrderDto : cartOrderDtoList) {
    		CartItem cartItem = cartItemRepo.findById(cartOrderDto.getCartItemId())
    				.orElseThrow(EntityNotFoundException::new);
    		
    		cartItemRepo.delete(cartItem);
    	}*/
    	
    	return orderId;
    }
}









