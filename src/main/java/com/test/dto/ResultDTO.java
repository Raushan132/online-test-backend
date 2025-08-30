package com.test.dto;

import lombok.Data;

@Data
public class ResultDTO {

    private Integer score;           
    private Integer total;          
    private Integer rank;
    private Double percentile;      
    private Integer correct;
    private Integer incorrect;
    private Integer unattempted;
    private Double accuracy;        
}
