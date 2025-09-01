package com.test.service;

import java.util.List;

import com.test.dto.QuestionDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.model.QuestionsEntity;
import com.test.model.TestSeriesEntity;

public interface IQuestionService {
	
     String saveQuestion(List<QuestionDTO> questionDto,TestSeriesEntity testSeriesEntity);
	
     List<QuestionOptionDTO> getAllQuestionDTOById(Integer testSeriesId);
	
	 List<QuestionsEntity> getAllQuestionEntityById(Integer testSeriesId);
	
	
	


}
