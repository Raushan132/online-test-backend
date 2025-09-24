package com.test.service.impl;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dto.AnswerRequestDTO;
import com.test.dto.ResultDTO;
import com.test.dto.SolutionDTO;
import com.test.model.QuestionsEntity;
import com.test.model.ScoreEntity;
import com.test.model.TestSeriesAttemptEntity;
import com.test.model.TestSeriesEntity;
import com.test.model.UserEntity;
import com.test.repository.ScoreRepository;
import com.test.repository.TestSeriesAttemptRepository;
import com.test.repository.TestSeriesRepository;
import com.test.repository.UserRepository;
import com.test.service.IAuthenticatedUserService;
import com.test.service.IQuestionService;
import com.test.service.ITestAttemptService;
import com.test.service.ITestSeriesService;

@Service
public class TestAttemptServiceImpl implements ITestAttemptService {

	@Autowired
	private IQuestionService iQuestionService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TestSeriesAttemptRepository attemptRepository;

	@Autowired
	private IAuthenticatedUserService userService;
	
	@Autowired
	private ScoreRepository scoreRepository;

	@Override
	public ResultDTO result(Integer testSeriesId, Integer attemptId, List<AnswerRequestDTO> answerRqstDTO) {
		try {

		ObjectMapper mapper = new ObjectMapper();
		ScoreEntity scoreEntity=null;
		TestSeriesAttemptEntity attemptEntity =attemptRepository.findById(attemptId).orElseThrow(()	->new RuntimeException("Attempt Entity not found"));
				Optional<ScoreEntity> optScoreEntity = scoreRepository.findByTestSeriesAttempt_AttemptId(attemptId);
		List<QuestionsEntity> entityById = iQuestionService.getAllQuestionEntityById(testSeriesId);
		if(optScoreEntity.isPresent()) {
			scoreEntity = optScoreEntity.get();
			ResultDTO resultDTO = new ResultDTO();
			resultDTO.setTotal(entityById.size());
		    resultDTO.setAccuracy(scoreEntity.getAccuracy());
		    resultDTO.setCorrect(scoreEntity.getNoOfCorrect());
		    resultDTO.setIncorrect(scoreEntity.getNoofIncorret());
		    resultDTO.setPercentile(scoreEntity.getPercentile());
		    resultDTO.setRank(scoreEntity.getTestRank());
		    resultDTO.setUnattempted(scoreEntity.getNoOfUnattempt());
		    resultDTO.setScore(scoreEntity.getScore());
		    String selectedAnswerStr = scoreEntity.getSelectedAnswer();
		    List<SolutionDTO> solutions = new LinkedList<>();
				Map<Integer, String> selectedOptions =
				        mapper.readValue(selectedAnswerStr, new TypeReference<Map<Integer, String>>() {});

				for (QuestionsEntity entity : entityById) {
					SolutionDTO sol = new SolutionDTO();
					modelMapper.map(entity, sol);
					sol.setChoosenOption(selectedOptions.get(entity.getQuestionId()));
					solutions.add(sol);

				}
				resultDTO.setSolution(solutions);
		    
			return resultDTO;
			
		}else {
			scoreEntity = new ScoreEntity();

			Map<Integer, String> correctAnswers = entityById.stream()
					.collect(Collectors.toMap(QuestionsEntity::getQuestionId, QuestionsEntity::getCorrectOption));
			int correct = 0;
			int incorrect = 0;
			int unattempted = 0;
			double totalMarks=0;
			for (AnswerRequestDTO data : answerRqstDTO) {
				Integer questionId = data.getQuestionId();
				String userAnswer = data.getSelectedOptions();
				if (userAnswer == null || userAnswer.isBlank()) {
					unattempted++;
				} else if (correctAnswers.get(questionId).equals(userAnswer)) {
					correct++;
					 
				} else {
					incorrect++;
				}
			}
			int total = entityById.size();
			double accuracy = total > 0 ? (correct * 100.0) / total : 0.0;
			ResultDTO resultDTO = new ResultDTO();
			resultDTO.setTotal(total);
			resultDTO.setCorrect(correct);
			resultDTO.setIncorrect(incorrect);
			resultDTO.setUnattempted(unattempted);
			resultDTO.setAccuracy(accuracy);
			resultDTO.setRank(0);
			resultDTO.setPercentile(0.0);
			List<SolutionDTO> solutions = new LinkedList<>();
			Map<Integer, String> selectedOptions = answerRqstDTO.stream()
					.collect(Collectors.toMap(AnswerRequestDTO::getQuestionId, AnswerRequestDTO::getSelectedOptions));
			for (QuestionsEntity entity : entityById) {
				SolutionDTO sol = new SolutionDTO();
				modelMapper.map(entity, sol);
				sol.setChoosenOption(selectedOptions.get(entity.getQuestionId()));
				if(entity.getCorrectOption().equalsIgnoreCase(selectedOptions.get(entity.getQuestionId()))){
					totalMarks+= entity.getMarks();
				}
				solutions.add(sol);

			}
			resultDTO.setScore(totalMarks);
			
		
			resultDTO.setSolution(solutions);
			/* store result to ScoreEntity Table below*/
			String selectedOptionsJson="";
			
				selectedOptionsJson = mapper.writeValueAsString(selectedOptions);
			
			scoreEntity.setSelectedAnswer(selectedOptionsJson);
			scoreEntity.setAccuracy(resultDTO.getAccuracy());
			scoreEntity.setNoOfCorrect(resultDTO.getCorrect());
			scoreEntity.setNoofIncorret(resultDTO.getIncorrect());
			scoreEntity.setPercentile(resultDTO.getPercentile());
			scoreEntity.setTestRank(resultDTO.getRank());
			scoreEntity.setNoOfUnattempt(resultDTO.getUnattempted());
			scoreEntity.setScore(totalMarks);
			
			scoreEntity.setTestSeriesAttempt(attemptEntity);
			scoreRepository.save(scoreEntity);
			
			return resultDTO;
		}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			
			throw new RuntimeException("Internal Server Error On TestAttemptServiceImpl");
		}
			
		
		
		
	}

	@Override
	public TestSeriesAttemptEntity createTestAttempt(TestSeriesEntity testSeries) {
		UserEntity user = userService.getCurrentUser();
		TestSeriesAttemptEntity attempt = new TestSeriesAttemptEntity();
		attempt.setAttemptDate(LocalDate.now());
		attempt.setNoOfAttempt(1);
		attempt.setUser(user);
		attempt.setTestSeries(testSeries);
		attempt = attemptRepository.save(attempt);
		return attempt;
	}

	@Override
	public List<TestSeriesAttemptEntity> getAttemptsByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTestAttemptId(TestSeriesEntity testSeries, Integer attemptId) {
		// TODO Auto-generated method stub
		Optional<TestSeriesAttemptEntity> optAttemptEntity = attemptRepository.findById(attemptId);
		if (optAttemptEntity.isPresent())
			return optAttemptEntity.get().getAttemptId();
		else {
			TestSeriesAttemptEntity attemptEntity = createTestAttempt(testSeries);
			return attemptEntity.getAttemptId();
		}

	}

}
