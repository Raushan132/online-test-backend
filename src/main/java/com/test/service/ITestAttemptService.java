package com.test.service;

import java.util.List;

import com.test.model.TestAttemptEntity;

public interface ITestAttemptService {
	
	TestAttemptEntity createTestAttempt(Integer userId,Integer testSeriesId);

	List<TestAttemptEntity> getAttemptsByUser(Integer userId);
}
