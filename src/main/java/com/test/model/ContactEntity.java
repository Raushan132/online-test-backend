package com.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ContactEntity {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer contactId;
	private String name;
	private String email;
	private String mobileNumber;
	private String description;
	

}
