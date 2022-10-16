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
	private Long rno;      //댓글 번호
	private String text;   //댓글 내용
	private String replyer;  //댓글 작성자
	private Long bno;    //게시글 번호
	private LocalDateTime regDate, modDate;  //등록일, 수정일
}
