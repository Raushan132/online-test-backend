package com.test.dto;

import java.util.List;

import lombok.Data;

@Data
public class AuthDTO {
	private String token;
	private List<String> userRole;
	private String username;
	
}
