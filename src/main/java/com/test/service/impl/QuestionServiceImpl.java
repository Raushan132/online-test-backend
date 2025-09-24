package com.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.QuestionDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.model.QuestionsEntity;
import com.test.model.TestSeriesEntity;
import com.test.repository.QuestionRepository;
import com.test.repository.TestSeriesAttemptRepository;
import com.test.service.IQuestionService;
import com.test.service.ITestAttemptService;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private QuestionRepository questionRepo;
	
	
 


	@Override
	public String saveQuestion(List<QuestionDTO> questionDTOs,TestSeriesEntity testSeriesEntity) {
		
		for (QuestionDTO questions : questionDTOs) {
			QuestionsEntity entity = modelMapper.map(questions, QuestionsEntity.class);
			entity.setTestSeries(testSeriesEntity);
			questionRepo.save(entity);
		}
		return "saved";
	}
	
	
	
	
	@Override
	public List<QuestionOptionDTO> getAllQuestionDTOById(Integer testSeriesId) {
	    List<QuestionsEntity> all = questionRepo.findAllByTestSeries_TestSeriesId(testSeriesId);
	    List<QuestionOptionDTO> dtoList = new ArrayList<>();
	    all.forEach(data -> {
	        QuestionOptionDTO mappedDto = modelMapper.map(data, QuestionOptionDTO.class);
	        dtoList.add(mappedDto);
	    });

	    return dtoList;
	}
	
	@Override
	public List<QuestionsEntity> getAllQuestionEntityById(Integer testSeriesId) {
		return  questionRepo.findAllByTestSeries_TestSeriesId(testSeriesId);
		
		
	}
	
	
	
	
	
	
	

	
	


}
