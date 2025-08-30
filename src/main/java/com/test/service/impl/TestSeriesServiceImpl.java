package com.test.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.AnswerRequestDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.dto.ResultDTO;
import com.test.dto.TestSeriesDTO;
import com.test.model.AnswerEntity;
import com.test.model.QuestionsEntity;
import com.test.model.TestAttemptEntity;
import com.test.model.TestSeriesEntity;
import com.test.repository.QuestionRepository;
import com.test.repository.TestAttemptRepository;
import com.test.repository.TestSeriesRepository;
import com.test.service.IQuestionService;
import com.test.service.ITestSeriesService;

@Service
public class TestSeriesServiceImpl implements ITestSeriesService {

    private final QuestionRepository questionRepository;

	@Autowired
	private IQuestionService iQuestionService;

	@Autowired
	private TestSeriesRepository testSeriesRepo;
	
	@Autowired
	private TestAttemptRepository attemptRepository;

	@Autowired
	private ModelMapper modelMapper;

    TestSeriesServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

	@Override
	public List<QuestionOptionDTO> getAllTestSeries(Integer testSeriesId) {
		List<QuestionOptionDTO> list = iQuestionService.getAllQuestionDTOById(testSeriesId);
		System.out.print("----------Test Service Executed-----------");
		System.out.println(list);
		return list;
	}

	@Override
	public List<TestSeriesDTO> getAllTestSeriesByCategory(String testCategory) {
		testCategory.toLowerCase();
		List<TestSeriesEntity> entity = testSeriesRepo.findTestSeriesIdByCategory(testCategory);
		System.err.print(entity);

		List<TestSeriesDTO> dtoList = entity.stream().map(data -> modelMapper.map(data, TestSeriesDTO.class))
				.collect(Collectors.toList());

		return dtoList;
	}

	@Override
	public List<TestSeriesDTO> getAllTestSeries() {
		List<TestSeriesEntity> entity = testSeriesRepo.findAll();
		System.err.println(entity);
		return entity.stream().map(data -> modelMapper.map(data, TestSeriesDTO.class)).collect(Collectors.toList());
		
	}
	
	
	
	

}
