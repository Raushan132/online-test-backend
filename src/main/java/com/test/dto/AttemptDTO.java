package com.test.dto;

import java.util.List;

import lombok.Data;

@Data
public class AttemptDTO {
	
	private Integer AttemptId;
	private List<QuestionOptionDTO> questionOptions;

}
