package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.QuestionDTO;
import com.test.repository.QuestionRepository;
import com.test.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService{
	
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@Override
	public String saveBook(QuestionDTO questionDto) {
		
		
		return null;
	}

}
