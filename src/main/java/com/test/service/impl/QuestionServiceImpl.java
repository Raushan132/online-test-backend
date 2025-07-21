package com.test.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.dto.QuestionDTO;
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

}
