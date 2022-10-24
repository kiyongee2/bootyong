package com.shop.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import com.shop.repository.ItemRepository;

@SpringBootTest
public class ItemRepositoryTest {
	
	@Autowired
	private ItemRepository itemRepo;
	
	//상품 1개 저장
	/*@Test
	public void itemInsert() {
		Item item = new Item();
		item.setItemNm("바지");
		item.setPrice(10000);
		item.setItemDetail("바지 상세 설명");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		//item.setRegTime(LocalDateTime.now());
		//item.setUpdateTime(LocalDateTime.now());
		
		Item savedItem = itemRepo.save(item);
		System.out.println(savedItem.toString());
	}*/
	
	//상품 10개 저장
	/*public void createItemList() {
		for(int i=1; i<=10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품 " + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명 " + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
		
			itemRepo.save(item);
		}
	}
	
	@Test
	public void findByItemNmTest() {
		this.createItemList();
		List<Item> itemList = itemRepo.findByItemNm("테스트 상품 5");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}*/
	
	/*@Test
	public void findByItemNmOrItemDetailTest() {
		List<Item> itemList = 
				itemRepo.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명 4");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}*/
	
	/*@Test
	public void findByPriceLessThanTest() {
		List<Item> itemList = itemRepo.findByPriceLessThan(10005);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}*/
	
	/*@Test
	public void findByItemDetailTest() {
		List<Item> itemList = itemRepo.findByItemDetail("상품 상세 설명");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}*/
	
	/*@Test
	public void createItemList2() {
		for(int i=1; i<=5; i++) {
			Item item = new Item();
			item.setItemNm("신상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("신상품 상세 설명 " + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
		
			itemRepo.save(item);
		}
		
		for(int i=6; i<=10; i++) {
			Item item = new Item();
			item.setItemNm("신상품 " + i);
			item.setPrice(10000 + i);
			item.setItemDetail("신상품 상세 설명 " + i);
			item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
			item.setStockNumber(0);
			
			itemRepo.save(item);
		}
	}*/
	
	//Querydsl 테스트
	@Test
	public void queryDslTest() {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QItem item = QItem.item;
		String itemDetail = "신상품 상세 설명";
		int price = 10003;
		String itemSellStatus = "SELL";
		
		booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
		booleanBuilder.and(item.price.gt(price));
		
		if(StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)) {
			booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
		}
		
		Pageable pageable = PageRequest.of(0, 5);
		Page<Item> itemPagingResult =
				itemRepo.findAll(booleanBuilder, pageable);
		System.out.println("total elements : " + itemPagingResult.getTotalElements() );
		
		List<Item> resultItemList = itemPagingResult.getContent();
		for(Item resultItem : resultItemList) {
			System.out.println(resultItem.toString());
		}
	}
	
}
