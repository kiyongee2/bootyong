package com.shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{
	
	@Query("select o from Orders o " +
            "where o.member.email = :email " +
            "order by o.orderDate desc"
    )
    List<Orders> findOrders(@Param("email") String email, Pageable pageable);

    @Query("select count(o) from Orders o " +
            "where o.member.email = :email"
    )
    Long countOrder(@Param("email") String email);
}
