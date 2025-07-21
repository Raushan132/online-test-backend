package com.test.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.model.TestAttemptEntity;
import com.test.model.TestSeriesEntity;
import com.test.model.UserEntity;
import com.test.repository.TestAttemptRepository;
import com.test.repository.TestSeriesRepository;
import com.test.repository.UserRepository;
import com.test.service.ITestAttemptService;

@Service
public class TestAttemptServiceImpl implements ITestAttemptService {

	@Autowired
	private TestAttemptRepository testAttemptRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TestSeriesRepository testSeriesRepo;

	
	@Override
	public TestAttemptEntity createTestAttempt(Integer userId, Integer testSeriesId) {
		UserEntity user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		TestSeriesEntity testSeriesEntity= testSeriesRepo.findById(testSeriesId).orElseThrow(() -> new RuntimeException("Test Series not found"));
		TestAttemptEntity attemptEntity = new TestAttemptEntity();
		attemptEntity.setUser(user);
		attemptEntity.setTestSeries(testSeriesEntity);
		attemptEntity.setAttemptDate(java.time.LocalDate.now());
		attemptEntity.setScore(0.0);
		attemptEntity.setCorrect(0);
		attemptEntity.setIncorrect(0);
		attemptEntity.setNotAttempted(0);
		return testAttemptRepo.save(attemptEntity);
	}
	
	
	@Override
	public List<TestAttemptEntity> getAttemptsByUser(Integer userId) {
		return testAttemptRepo.findAll()
				.stream()
				.filter(attempt-> attempt.getUser().getUserId().equals(userId))
				.toList();
	}
	
	

}
