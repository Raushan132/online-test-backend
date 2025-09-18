package com.test.dto;

import java.time.LocalDate;
import java.util.List;

import com.test.model.QuestionsEntity;
import com.test.model.TestSeriesAttemptEntity;

import lombok.Data;

@Data
public class TestSeriesDTO {

	private Integer testSeriesId;
	private String title;
	private String description;
	private int totalQuestion;
	private double totalMarks;
	private Integer duration;
	private String category;
	private String subject;
	private String topic;
	private double price;
	private Integer testListId;
	private LocalDate createAt;
	private List<TestSeriesAttemptEntity> attempts;
	private List<QuestionsEntity> questions;

}
