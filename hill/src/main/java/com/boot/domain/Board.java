package com.boot.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude="member")
@Getter
@Setter
@Entity
public class Board{
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="board_id")
	private Long seq;
	
	private String title;
	
	private String content;
	
	@Column(updatable=false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createDate;
	
	@Column(updatable=false, 
			columnDefinition = "bigint DEFAULT 0")
	private Long cnt = 0L;
	
	@ManyToOne
	@JoinColumn(name="member_id", nullable=false, updatable=false)
	private Member member; 
}










