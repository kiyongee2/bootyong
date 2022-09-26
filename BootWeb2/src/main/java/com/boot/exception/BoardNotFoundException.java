package com.boot.exception;

public class BoardNotFoundException extends BoardException{

	private static final long serialVersionUID = 41L;

	public BoardNotFoundException(String message) {
		super(message);
	}
}
