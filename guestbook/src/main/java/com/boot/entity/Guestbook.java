package com.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Guestbook extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gno;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(length = 1500, nullable = false)
	private String content;
	
	@Column(length = 50, nullable = false)
	private String writer;
	
	//수정 시간 테스트용 메서드 추가
	public void changeTitle(String title) {
		this.title = title;
	}
	
	public void changeContent(String content) {
		this.content = content;
	}
}








