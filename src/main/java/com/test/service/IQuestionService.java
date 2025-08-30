package com.test.service;

import java.util.List;

import com.test.dto.QuestionDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.model.QuestionsEntity;

public interface IQuestionService {
	
	public abstract String saveQuestion(QuestionDTO questionDto);
	
	public abstract List<QuestionOptionDTO> getAllQuestionDTOById(Integer testSeriesId);
	
	public abstract List<QuestionsEntity> getAllQuestionEntityById(Integer testSeriesId);
	
	
	


}
