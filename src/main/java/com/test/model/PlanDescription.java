package com.test.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PlanDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planDescriptionId;

    private String title;
    private String description;
    private String duration;

    @OneToMany(mappedBy = "planDescription")
    private List<PlanEntity> plans;
}
