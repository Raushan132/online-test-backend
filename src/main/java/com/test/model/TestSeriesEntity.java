package com.test.model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private Integer duration;

	private double price;
	private LocalDate createAt;
	
	  // ✅ EAGER fetching ensures we load them when TestSeries is loaded
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties({"subjects"}) // prevents infinite recursion
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnoreProperties({"topics", "category"}) // avoid recursion again
    private SubjectEntity subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    @JsonIgnoreProperties({"subject", "category"}) 
    private TopicEntity topic;
	
	@OneToMany(mappedBy = "testSeries",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<TestAttemptEntity> attempts;

	
	

}
