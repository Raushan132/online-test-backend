package com.test.dto;

import lombok.Data;

@Data
public class AnswerRequestDTO {
	
	private Integer questionId;
	private String selectedOptions;
}
