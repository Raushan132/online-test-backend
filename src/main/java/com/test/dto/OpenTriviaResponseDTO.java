package com.test.dto;

import java.util.List;

import lombok.Data;

@Data
public class OpenTriviaResponseDTO {
    private int response_code;
    private List<QuestionAPIData> results;
}
