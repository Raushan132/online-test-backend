package com.test.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ScoreEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scoreId;
	private LocalDate submissionTime;
	private double score;
	private Integer testRank;
	private Integer outOfRank;
	private LocalDate timeTaken;
	private Integer selectedAnswer;
	private Integer noOfCorrect;
	private Integer noofIncorret;
	private Integer noOfUnattempt;
	
	
	 /**
     * Many scores belong to a single test attempt.
     * Foreign Key: attempt_id (from 'test_series_attempt' table)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private TestSeriesAttemptEntity testSeriesAttempt;

    
    
}
