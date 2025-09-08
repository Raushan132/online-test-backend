package com.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class FeedbackEntity {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer feedbackId;
	private Integer testSeriesId;
	private String description;
	private Integer userId;

}
