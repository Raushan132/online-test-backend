package com.test.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class HttpResponseDTO <T>{
	
	private HttpStatus statusCode;
	private String message;
	private T data;

	public HttpResponseDTO(HttpStatus statusCode, String message, T data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public static <T> HttpResponseDTO<T> of(HttpStatus status, String message, T data) {
		return new HttpResponseDTO<>(status, message, data);
	}

}
