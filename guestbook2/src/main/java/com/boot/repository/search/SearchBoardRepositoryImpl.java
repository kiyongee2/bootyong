package com.boot.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.boot.entity.Board;
import com.boot.entity.QBoard;
import com.boot.entity.QMember;
import com.boot.entity.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport
implements SearchBoardRepository{

	public SearchBoardRepositoryImpl() {
		super(Board.class);
	}

	@Override
	public Board search1() {
		log.info("search1......................");  //동작 확인
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		QMember member = QMember.member;
		
		//jpqlQuery.select(board).where(board.bno.eq(1L));
		//log.info(jpqlQuery);
		//List<Board> result = jpqlQuery.fetch(); //fethch()로 가져옴 
		
		JPQLQuery<Board> jpqlQuery = from(board);
		jpqlQuery.leftJoin(member).on(board.writer.eq(member));
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
		
		JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());
		tuple.groupBy(board);
		
		log.info("--------------------------------------------");
		log.info(tuple);
		log.info("--------------------------------------------");
		
		List<Tuple> result = tuple.fetch();
		log.info(result);
		
		return null;
	}

	@Override
	public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
		log.info("searchPage...............................");
		
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		QMember member = QMember.member;
		
		JPQLQuery<Board> jpqlQuery = from(board);
		jpqlQuery.leftJoin(member).on(board.writer.eq(member));
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
		
		JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		BooleanExpression expression = board.bno.gt(0L);
		
		booleanBuilder.and(expression);
		
		//검색 조건 설정
		if(type != null) {
			String[] typeArr = type.split("");
			BooleanBuilder conditonBuilder = new BooleanBuilder();
			
			for(String t : typeArr) {
				switch(t) {
				case "t":
					conditonBuilder.or(board.title.contains(keyword));
					break;
				case "w":
					conditonBuilder.or(member.email.contains(keyword));
					break;
				case "c":
					conditonBuilder.or(board.content.contains(keyword));
					break;
				}
			}
			booleanBuilder.and(conditonBuilder);
		}
		tuple.where(booleanBuilder);
		
		//검색 테스트
		/*tuple.groupBy(board);
		List<Tuple> result = tuple.fetch();
		log.info(result);*/
		
		//정렬 처리 추가
		Sort sort = pageable.getSort();
		
		sort.stream().forEach(order -> {
			Order direction = order.isAscending() ? Order.ASC : Order.DESC;
			String prop = order.getProperty();
			
			PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
			tuple.orderBy(new OrderSpecifier<>(direction, orderByExpression.get(prop)));
		});
		tuple.groupBy(board);
		
		//page 처리
		tuple.offset(pageable.getOffset());
		tuple.limit(pageable.getPageSize());
		
		List<Tuple> result = tuple.fetch();
		log.info(result);
		long count = tuple.fetchCount();
		
		log.info("count: " + count);
	
		return new PageImpl<Object[]>(
				result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
				pageable,
				count);
	}

}





