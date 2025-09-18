package com.test.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.AnswerRequestDTO;
import com.test.dto.ResultDTO;
import com.test.dto.SolutionDTO;
import com.test.model.QuestionsEntity;
import com.test.model.TestSeriesAttemptEntity;
import com.test.model.TestSeriesEntity;
import com.test.model.UserEntity;
import com.test.repository.TestSeriesRepository;
import com.test.repository.UserRepository;
import com.test.service.IQuestionService;
import com.test.service.ITestAttemptService;

@Service
public class TestAttemptServiceImpl implements ITestAttemptService {



	@Autowired
	private TestSeriesRepository testSeriesRepo;
	
	@Autowired
	private IQuestionService iQuestionService;
	
	@Autowired
	private ModelMapper modelMapper;

	

	
	
	
	
	@Override
	public ResultDTO result(Integer testSeriesId, List<AnswerRequestDTO> answerRqstDTO) {
		
		List<QuestionsEntity> entityById = iQuestionService.getAllQuestionEntityById(testSeriesId);
		
		
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
	List<SolutionDTO> solutions = new LinkedList<>();
	Map<Integer,String> selectedOptions=	answerRqstDTO.stream()
			.collect(Collectors.toMap(AnswerRequestDTO::getQuestionId, AnswerRequestDTO::getSelectedOptions));
	for(QuestionsEntity entity: entityById) {
		SolutionDTO  sol = new SolutionDTO();
		modelMapper.map(entity, sol);
		sol.setChoosenOption(selectedOptions.get(entity.getQuestionId()));
		solutions.add(sol);
		
	}
	
	resultDTO.setSolution(solutions);		

		return resultDTO;
	}







	@Override
	public TestSeriesAttemptEntity createTestAttempt(Integer userId, Integer testSeriesId) {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public List<TestSeriesAttemptEntity> getAttemptsByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
