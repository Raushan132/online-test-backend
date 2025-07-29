package com.test.service;

import java.util.List;

import com.test.dto.QuestionDTO;
import com.test.dto.QuestionOptionDTO;

public interface IQuestionService {
	
	public abstract String saveQuestion(QuestionDTO questionDto);
	
	public abstract List<QuestionOptionDTO> getAllQuestionEntityById(Integer testSeriesId);
	


}
