package com.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceResult {
	boolean result;
	String message;
	
	public ServiceResult(boolean result) {
		this.result = result;
	}
}
