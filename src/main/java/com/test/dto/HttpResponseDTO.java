package com.test.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseDTO <T>{
	
	private HttpStatus statusCode;
	private String message;
	private T data;

}
