package com.test.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planId;
    private double price;
    private double discount;
    private boolean isActive;
    private String discountPeriod;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "plan_description_id") // FK to PlanDescription
    private PlanDescription planDescription;

    @OneToMany(mappedBy = "plan")
    private List<SubscriberEntity> subscribers;
}

