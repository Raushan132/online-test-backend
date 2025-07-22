package com.test.service;

import java.util.List;

import com.test.dto.QuestionDTO;
import com.test.dto.QuestionOptionDTO;

public interface IQuestionService {
	
	String saveQuestion(QuestionDTO questionDto);
	
	List<QuestionOptionDTO> getAllQuestionEntityById(Integer testSeriesId);
	
	


}
