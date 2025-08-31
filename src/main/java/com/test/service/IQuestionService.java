package com.test.service;

import java.util.List;

import com.test.dto.QuestionDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.model.QuestionsEntity;

public interface IQuestionService {
	
     String saveQuestion(QuestionDTO questionDto);
	
     List<QuestionOptionDTO> getAllQuestionDTOById(Integer testSeriesId);
	
	 List<QuestionsEntity> getAllQuestionEntityById(Integer testSeriesId);
	
	
	


}
