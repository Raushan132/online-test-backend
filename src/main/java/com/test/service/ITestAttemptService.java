package com.test.service;

import java.util.List;

import com.test.dto.AnswerRequestDTO;
import com.test.dto.ResultDTO;
import com.test.model.TestSeriesAttemptEntity;

public interface ITestAttemptService {
	
	TestSeriesAttemptEntity createTestAttempt(Integer userId,Integer testSeriesId);

	List<TestSeriesAttemptEntity> getAttemptsByUser(Integer userId);
	
	ResultDTO result(Integer testSeriesId,List<AnswerRequestDTO> answerRqstDTO );
		
	
	
	
}
