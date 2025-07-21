package com.test.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuestionAPIData {
    private String question;
    
    @JsonProperty("correct_answer")
    private String correctAnswer;
    
    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers;
}
