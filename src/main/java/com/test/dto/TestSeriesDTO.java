package com.test.dto;

import java.time.LocalDate;
import java.util.List;

import com.test.model.QuestionsEntity;
import com.test.model.TestAttemptEntity;

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
	private double price;
	private LocalDate createAt;
	private List<TestAttemptEntity> attempts;
	private List<QuestionsEntity> questions;

}
