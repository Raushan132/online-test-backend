package com.test.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TestSeriesAttemptEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attemptId;
	private LocalDate attemptDate;
	private Integer noOfAttempt;
	
	/**
     * One attempt can have multiple scores (e.g., section-wise scoring).
     */
	 @OneToOne(mappedBy = "testSeriesAttempt", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	    private ScoreEntity score;

    /**
     * (Optional, based on ERD)
     * Attempt also belongs to a specific user and test series.
     * These would be additional relationships:
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;  // FK to user table

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_series_id", nullable = false)
    private TestSeriesEntity testSeries;  // FK to test_series table

	

}
