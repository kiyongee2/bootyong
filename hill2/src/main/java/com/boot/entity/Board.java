package com.boot.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude="writer")
@Getter @Setter
@Entity
public class Board extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	
	private String title;
	
	private String content;
	
	@Column(updatable = false,
			columnDefinition = "bigint DEFAULT 0")
	private Long cnt = 0L;
	
	//다대일 연관 매핑
	@ManyToOne(fetch = FetchType.LAZY)
	private Member writer;
	
	//제목 수정
	public void changeTitle(String title) {
		this.title = title;
	}
	
	//내용 수정
	public void changeContent(String content) {
		this.content = content;
	}
}
