package com.test.service;

import java.util.List;

import com.test.model.CategoryEntity;
import com.test.model.SubjectEntity;
import com.test.model.TopicEntity;

public interface IClassificationService {
	
	CategoryEntity getCategoryByName(String categoryName);
	
	SubjectEntity getSubjectByName(String subjectName,String categoryName);
	
	TopicEntity getTopicByTopicName(String topicName,String subjectName,String categoryName);
	
	List<CategoryEntity> getAllCategoryEntity();
	List<SubjectEntity> getAllSubjectEntity();
	List<TopicEntity> getAllTopicEntity();

}
