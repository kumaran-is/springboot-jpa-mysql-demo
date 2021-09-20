package com.college.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_contact_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentContactInfo extends AbstractEntity {

	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
    private String street;
	
	@Column(nullable = false)
    private String city;
	
	@Column(nullable = false)
    private String state;
	
	@Column(nullable = false)
    private String zipcode;
	
	@OneToOne(mappedBy = "studentContactInfo")
	private Student student;
}
