package com.test.dto;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import com.test.model.QuestionsEntity;
import com.test.model.TestAttemptEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class TestSeriesDTO {

	private Integer testSeriesId;
	private String title;
	private String description;
	private int totalQuestion;
	private double totalMarks;
	private Duration duration;
	private String category;
	private double price;
	private LocalDate createAt;
	private List<TestAttemptEntity> attempts;
	private List<QuestionsEntity> questions;

}
