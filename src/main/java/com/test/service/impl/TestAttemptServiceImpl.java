package com.test.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.AnswerRequestDTO;
import com.test.dto.ResultDTO;
import com.test.model.QuestionsEntity;
import com.test.model.TestAttemptEntity;
import com.test.model.TestSeriesEntity;
import com.test.model.UserEntity;
import com.test.repository.TestAttemptRepository;
import com.test.repository.TestSeriesRepository;
import com.test.repository.UserRepository;
import com.test.service.IQuestionService;
import com.test.service.ITestAttemptService;

@Service
public class TestAttemptServiceImpl implements ITestAttemptService {

	@Autowired
	private TestAttemptRepository testAttemptRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TestSeriesRepository testSeriesRepo;
	
	@Autowired
	private IQuestionService iQuestionService;

	
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
	
	
	
	
	@Override
	public ResultDTO result(Integer testSeriesId, List<AnswerRequestDTO> answerRqstDTO) {
		
		List<QuestionsEntity> entityById = iQuestionService.getAllQuestionEntityById(testSeriesId);
		
		System.out.println(entityById);
	Map<Integer,String> correctAnswers=	entityById.stream()
		.collect(Collectors.toMap(QuestionsEntity::getQuestionId, QuestionsEntity::getCorrectOption));
	int correct =0;
	int incorrect=0;
	int unattempted=0;
	for(AnswerRequestDTO data: answerRqstDTO) {
		Integer questionId = data.getQuestionId();
		String userAnswer = data.getSelectedOptions();
		if(userAnswer== null || userAnswer.isBlank()) {
			unattempted++;
		}else if(correctAnswers.get(questionId).equals(userAnswer)) {
			correct++;
		}else {
			incorrect++;
		}
	}
	int total =entityById.size();
	double accuracy=total>0?(correct*100.0)/total:0.0;
	ResultDTO resultDTO = new ResultDTO();
	resultDTO.setScore(correct);
	resultDTO.setTotal(total);
	resultDTO.setCorrect(correct);
	resultDTO.setIncorrect(incorrect);
	resultDTO.setUnattempted(unattempted);
	resultDTO.setAccuracy(accuracy);
	resultDTO.setRank(0);
	resultDTO.setPercentile(0.0);

		return resultDTO;
	}
	
	
	
	

}
