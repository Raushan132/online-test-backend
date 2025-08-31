package com.test.dto;

import jakarta.persistence.Column;
import lombok.Data;
@Data
public class QuestionOptionDTO {
	

	@Column(length = 1000)
	private String questionTxt;
	private Integer questionId;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String optionE;
	private int marks;

}
