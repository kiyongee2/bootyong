package com.boot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BoardDto {
	private Long bno;
	private String title;
	private String content;
	private String writerUserid;   //작성자의 아이디
	private String writerName;     //작성자의 이름
	private Long cnt = 0L;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private int replyCount;      //해당 게시글의 댓글 수
}




