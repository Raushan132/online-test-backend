package com.test.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClassificationDTO {
		
	List<String> categoryNames;
	List<String> subjectNames;
	List<String> topicNames;
	
}
