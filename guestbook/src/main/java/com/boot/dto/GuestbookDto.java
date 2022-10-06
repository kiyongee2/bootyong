package com.boot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GuestbookDto {

	private Long gno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate, modDate;
}
