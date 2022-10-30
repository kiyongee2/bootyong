package com.shop.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.dto.CartDetailDto;
import com.shop.dto.CartItemDto;
import com.shop.dto.CartOrderDto;
import com.shop.dto.OrderDto;
import com.shop.service.CartService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CartController {
	
	private final CartService cartService;
	
	//장바구니 담기
	@PostMapping("/cart")
	public @ResponseBody ResponseEntity<?> order(@RequestBody @Valid CartItemDto cartItemDto,
                        BindingResult bindingResult, Principal principal){
		//장바구니에 담을 상품 정보를 받는 cartItemDto 객체에 데이터 바인딩 시 에러가 있는지 검사
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {  
                sb.append(fieldError.getDefaultMessage());
            }
            //에러 정보를 ResponseEntity 객체에 담아서 전달
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();  //현재 로그인한 회원의 이메일 정보를 저장
        Long cartItemId;
        try {
           cartItemId = cartService.addCart(cartItemDto, email);   //장바구니에 상품을 담는 로직 호출
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        //결과값으로 생성된 장바구니 상품 아이디와 요청이 성공했다는 HTTP 응답 상태 코드를 반환
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }
	
	//장바구니 목록 보기
	@GetMapping("/cart")
	public String orderHist(Principal principal, Model model) {
		List<CartDetailDto> cartDetailList =
				cartService.getCartList(principal.getName());
		model.addAttribute("cartItems", cartDetailList);
		return "cart/cartList";
	}
	
	//장바구니 상품의 수량 업데이트
	@PatchMapping("/cartItem/{cartItemId}")  //@PATCH는 요청된 자원의 일부를 업데이트
	public @ResponseBody ResponseEntity<?> updateCartItem(
			@PathVariable("cartItemId") Long cartItemId, int count,
			Principal principal){
		if(count <= 0) {
			return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
		}else if(!cartService.validateCartItem(cartItemId, principal.getName())) {
			return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
		}
		
		cartService.updateCartItemCount(cartItemId, count);
		return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
	}
	
	//장바구니 상품 삭제 요청
	@DeleteMapping("/cartItem/{cartItemId}")
	public @ResponseBody ResponseEntity<?> deleteCartItem(
			@PathVariable("cartItemId") Long cartItemId,
			Principal principal){
		if(!cartService.validateCartItem(cartItemId, principal.getName())) {
			return new ResponseEntity<String>("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
		}
		
		cartService.deleteCartItem(cartItemId);
		return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
	}
	
	//장바구니 상품 주문하기
	@PostMapping("/cart/orders")
	public @ResponseBody ResponseEntity<?> orderCartItem(
			@RequestBody CartOrderDto cartOrderDto, Principal principal){
		List<CartOrderDto> cartOrderDtoList =
				cartOrderDto.getCartOrderDtoList();
		
		if(cartOrderDtoList == null || cartOrderDtoList.size() == 0) {
			return new ResponseEntity<String>("주문할 상품을 선택하세요", HttpStatus.BAD_REQUEST);
		}
		
		for(CartOrderDto cartOrder : cartOrderDtoList) {
			if(!cartService.validateCartItem(cartOrder.getCartItemId(), principal.getName())) {
				return new ResponseEntity<String>("주문 권한이 없습니다.", HttpStatus.FORBIDDEN); 
			}
		}
		
		Long orderId = cartService.orderCartItem(cartOrderDtoList, principal.getName());
		
		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}
}








