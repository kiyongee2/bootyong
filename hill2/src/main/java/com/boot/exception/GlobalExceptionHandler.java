package com.boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice //예외 처리 전용 컨트롤러
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class) //일반적인 코드 및 구문 오류 처리(500)
	public String handleException(Exception exception,
			Model model) {
		model.addAttribute("exception", exception);
		return "errors/globalError";
	}
	
	//404 - 페이지를 찾을 수 없을때 예외 처리
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException e) {
		return "errors/error404";
	}
}
