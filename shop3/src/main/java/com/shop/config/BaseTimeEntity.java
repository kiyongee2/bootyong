package com.shop.config;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(value= {AuditingEntityListener.class})
@MappedSuperclass  //부모 클래스를 상속 받는 자식 클래스의 매핑 정보만 제공함
@Getter @Setter
public abstract class BaseTimeEntity {
	
	//엔티티가 생성되어 저장될 때 시간을 자동으로 저장함
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regTime;
	
	//엔티티의 값을 변경할 때 시간을 자동으로 저장함
	@LastModifiedDate
	private LocalDateTime updateTime;
	
}
