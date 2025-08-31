package com.test.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SolutionDTO {
	private Integer questionId;
	private String questionTxt;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String optionE;
	private String correctOption;
	private String choosenOption;
	private int marks;
	@Column(length = 2000)
	private String explanation;

}
