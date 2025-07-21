package com.test.model;

import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer answerId;
	private String selectOption;
	private boolean isCorrect;
	
	@ManyToOne
	@JoinColumn(name = "attempt_id")
	private TestAttemptEntity attempt;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private QuestionsEntity question;
	

}
