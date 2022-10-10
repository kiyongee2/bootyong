package com.boot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReplyDto {
	
	private Long rno;
	private String text;
	private String replyer;
	private Long bno;
	private LocalDateTime regDate, modDate;
}
