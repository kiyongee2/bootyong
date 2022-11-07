package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.dto.CartDetailDto;
import com.shop.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	//카트 아이디와 상품 아이디를 이용해서 상품이 장바구니에 들어있는지 조회
	CartItem findByCartIdAndItemId(Long cartId, Long itemId);
	
	//장바구니 페이지 전달할 cartDetailDto 리스트 조회 쿼리(JPQL문)
	//new 키워드와 패키지, 클래스 이름 명시(필드는 순서대로 적어줌)
	@Query("SELECT new com.shop.dto.CartDetailDto(ci.id, "
			+ "i.itemNm, i.price, ci.count, im.imgUrl) "
		 + "FROM CartItem ci, ItemImg im "
		 + "JOIN ci.item i "
		 + "WHERE ci.cart.id = :cartId "
		 + "AND im.item.id = ci.item.id "
		 + "AND im.repimgYn = 'Y' "
		 + "ORDER BY ci.regTime DESC"
		)
	List<CartDetailDto> findCartDetailDtoList(Long cartId);
}
