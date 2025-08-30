package com.test.model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TestSeriesEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer testSeriesId;
	private String title;
	private String imgUrl;
	private String description;
	private int totalQuestion;
	private double totalMarks;
	private Duration duration;
	private String category;
	private double price;
	private LocalDate createAt;
	
	@OneToMany(mappedBy = "testSeries",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<TestAttemptEntity> attempts;

	
	

}
