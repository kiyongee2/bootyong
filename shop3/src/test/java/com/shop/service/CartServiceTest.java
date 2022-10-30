package com.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.CartItemDto;
import com.shop.entity.CartItem;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.repository.CartItemRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;

@Transactional
@SpringBootTest
public class CartServiceTest {
	@Autowired
	ItemRepository itemRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartItemRepository cartItemRepo;
	
	//상품 저장
	public Item saveItem(){
        Item item = new Item();
        item.setItemNm("test 상품");
        item.setPrice(2000);
        item.setItemDetail("test 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepo.save(item);
    }

	//회원 저장
    public Member saveMember(){
        Member member = new Member();
        member.setEmail("test@cart.com");
        return memberRepo.save(member);
    }
    
    @Test
    public void addCart() {
    	Item item = saveItem();
    	Member member = saveMember();
    	
    	CartItemDto cartItemDto = new CartItemDto();
    	cartItemDto.setItemId(item.getId());
    	cartItemDto.setCount(5);
    	
    	Long cartItemId = cartService.addCart(cartItemDto, member.getEmail());
    	CartItem cartItem = cartItemRepo.findById(cartItemId)
    			.orElseThrow(EntityNotFoundException::new);
    	
    	//상품 아이디와 저장된 상품아이디가 같으면 테스트 통과
    	assertEquals(item.getId(), cartItem.getItem().getId());
    	//장바구니에 담은 수량과 실제로 장바구니에 저장된 수량이 같으면 테스트 통과
    	assertEquals(cartItemDto.getCount(), cartItem.getCount());
    }
	
	
}
