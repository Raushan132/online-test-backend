package com.test.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.QuestionDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.model.QuestionsEntity;
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
		QuestionsEntity save = questionRepo.save(entity);
		return "saved";
	}
	@Override
	public List<QuestionOptionDTO> getAllQuestionEntityById(Integer testSeriesId) {

	    List<QuestionsEntity> all = questionRepo.findAllByTestSeries_TestSeriesId(testSeriesId);
	    List<QuestionOptionDTO> dto = new ArrayList<>();

	    all.forEach(data -> {
	        QuestionOptionDTO mappedDto = modelMapper.map(data, QuestionOptionDTO.class);
	        dto.add(mappedDto);
	    });

	    return dto;
	}



}
