package com.boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileDto {
	private String uuid;        //파일 이름을 unique하게 만들 필드
	private String fileName;    //파일 이름
	private String contentType; //파일 유형
	
	public FileDto() {}
	
	public FileDto(String uuid, String fileName, String contentType) {
		this.uuid = uuid;
		this.fileName = fileName;
		this.contentType = contentType;
	}
}
