package com.boot.exception;

public class BoardException extends RuntimeException{

	private static final long serialVersionUID = 31L;
	
	public BoardException(String message) {
		super(message);
	}

}
