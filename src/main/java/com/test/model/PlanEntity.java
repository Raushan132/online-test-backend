package com.test.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private String title;
	private String description;
	private double price;
	private double discount;
	private Integer durationDay;
	private boolean isActive;
	private String discountPeriod;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private Integer userId;
	@OneToMany(mappedBy = "plan")
	private List<SubscriberEntity> subscribers;
	

}
