package com.test.service;

import java.util.List;

import com.test.dto.AnswerRequestDTO;
import com.test.dto.ResultDTO;
import com.test.model.TestSeriesAttemptEntity;
import com.test.model.TestSeriesEntity;

public interface ITestAttemptService {
	
	TestSeriesAttemptEntity createTestAttempt(TestSeriesEntity testSeries);
	/***
	 * <h3>Check the Attempt Id exits or not</h3>
	 * <h4>If attempt Id doesn't exits then create a new one and return new attemptId<br/> else return exited one</h4>  
	 * 
	 *  
	 * @param testSeriesId
	 * @param attemptId
	 * @return attemptId
	 */
	Integer getTestAttemptId(TestSeriesEntity testSeries,Integer attemptId);

	List<TestSeriesAttemptEntity> getAttemptsByUser(Integer userId);
	
	ResultDTO result(Integer testSeriesId,Integer attemptId,List<AnswerRequestDTO> answerRqstDTO );
		
	
	
	
}
