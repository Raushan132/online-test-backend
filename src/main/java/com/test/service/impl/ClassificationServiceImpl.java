package com.test.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.CategoryEntity;
import com.test.model.SubjectEntity;
import com.test.model.TopicEntity;
import com.test.repository.CategoryRepository;
import com.test.repository.SubjectRepository;
import com.test.repository.TopicRepository;
import com.test.service.IClassificationService;

@Service
public class ClassificationServiceImpl implements IClassificationService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private TopicRepository topicRepository;

	@Override
	public CategoryEntity getCategoryByName(String categoryName) {
		Optional<CategoryEntity> optEntity = categoryRepository.findByCategoryName(categoryName);
		if (optEntity.isPresent())
			return optEntity.get();
		CategoryEntity entity = new CategoryEntity();
		entity.setCategoryName(categoryName);
		entity = categoryRepository.save(entity);
		return entity;
	}

	@Override
	public SubjectEntity getSubjectByName(String subjectName, String categoryName) {

		Optional<SubjectEntity> optEntity = subjectRepository.findBySubjectName(subjectName);
		if (optEntity.isPresent())
			return optEntity.get();
		SubjectEntity entity = new SubjectEntity();
		entity.setSubjectName(subjectName);
		CategoryEntity catEntity = getCategoryByName(categoryName);
		entity.setCategory(catEntity);
		entity = subjectRepository.save(entity);

		return entity;
	}

	@Override
	public TopicEntity getTopicByTopicName(String topicName, String subjectName, String categoryName) {
		// TODO Auto-generated method stub
		Optional<TopicEntity> optEntity = topicRepository.findByTopicName(topicName);
		if (optEntity.isPresent())
			return optEntity.get();
		SubjectEntity subjectEntity = getSubjectByName(subjectName, categoryName);
		TopicEntity entity = new TopicEntity();
		entity.setTopicName(topicName);
		entity.setSubject(subjectEntity);
		entity = topicRepository.save(entity);
		return entity;
	}

	@Override
	public List<CategoryEntity> getAllCategoryEntity() {
		// TODO Auto-generated method stub
		
		return categoryRepository.findAll();
	}

	@Override
	public List<SubjectEntity> getAllSubjectEntity() {
		// TODO Auto-generated method stub
		return subjectRepository.findAll();
	}

	@Override
	public List<TopicEntity> getAllTopicEntity() {
		// TODO Auto-generated method stub
		return topicRepository.findAll();
	}

}
