package com.shop.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Cart;
import com.shop.entity.Member;
import com.shop.repository.CartRepository;
import com.shop.repository.MemberRepository;

@Transactional
@SpringBootTest
public class CartTest {
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	PasswordEncoder pwencoder;
	
	@PersistenceContext
	EntityManager em;
	
	public Member createMember() {
		MemberFormDto memberFormDto = new MemberFormDto();
		memberFormDto.setEmail("test2@cart.com");
		memberFormDto.setName("홍길동");
		memberFormDto.setAddress("인천광역시 부평구");
		memberFormDto.setPassword("1234");
		return Member.createMember(memberFormDto, pwencoder);
	}
	
	@Test
	public void findCartAndMemberTest() {
		Member member = this.createMember();
		memberRepo.save(member);  //member 저장
		
		Cart cart = new Cart();
		cart.setMember(member);
		cartRepo.save(cart);    //cart 저장
		
		//JPA는 회원 엔티티와 장바구니 엔티티를 영속성 턴텍스트에 저장 후 엔티티 매니저로부터 
		//강제로 flush()를 호출하여 DB에 반영함
		em.flush();
		
		//장바구니 엔티티와 회원 엔티티를 같이 가져오는 보기 위해 영속성 컨텍스트를 비워줌
		em.clear();  
		
		Cart savedCart = cartRepo.findById(cart.getId())
							.orElseThrow(EntityNotFoundException::new);
		
		//처음에 저장된 member 엔티티의 id와 savedCart에 매핑된 member 엔티티의 id를 비교
		assertEquals(savedCart.getMember().getId(), member.getId());
	}
}









