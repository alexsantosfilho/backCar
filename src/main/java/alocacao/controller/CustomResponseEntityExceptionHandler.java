package alocacao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import alocacao.exceptions.CustomRuntimeException;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Map<String, String>> handleCustomRuntimeException(CustomRuntimeException exception,
			WebRequest request) {

		Map<String, String> response = new HashMap<String, String>();
		response.put(exception.getField(), exception.getMessage());

		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

}
