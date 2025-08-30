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
import com.test.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private QuestionRepository questionRepo;

	@Override
	public String saveQuestion(QuestionDTO questionDto) {
		QuestionsEntity entity = modelMapper.map(questionDto, QuestionsEntity.class);
		questionRepo.save(entity);
		return "saved";
	}
	@Override
	public List<QuestionOptionDTO> getAllQuestionEntityById(Integer testSeriesId) {
	    List<QuestionsEntity> all = questionRepo.findAllByTestSeries_TestSeriesId(testSeriesId);
	    List<QuestionOptionDTO> dtoList = new ArrayList<>();
	    all.forEach(data -> {
	        QuestionOptionDTO mappedDto = modelMapper.map(data, QuestionOptionDTO.class);
	        dtoList.add(mappedDto);
	    });

	    return dtoList;
	}
	
	
	
	
	

	
	


}
