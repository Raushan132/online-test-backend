package com.test.service;

import java.util.List;

import com.test.dto.AnswerRequestDTO;
import com.test.dto.ResultDTO;
import com.test.model.TestAttemptEntity;

public interface ITestAttemptService {
	
	TestAttemptEntity createTestAttempt(Integer userId,Integer testSeriesId);

	List<TestAttemptEntity> getAttemptsByUser(Integer userId);
	
	ResultDTO result(Integer testSeriesId,List<AnswerRequestDTO> answerRqstDTO );
		
	
	
	
}
