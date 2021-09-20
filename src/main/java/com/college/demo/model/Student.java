package com.college.demo.model;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends AbstractEntity{

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	
	@Column(columnDefinition = "DATE", nullable = false)
	private LocalDate dob;
	
	@OneToOne
	@JoinColumn(name = "student_contact_info_id")
	private StudentContactInfo studentContactInfo;

	@Transient
	private Integer age;

	public Integer getAge() {
		return Period.between(dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
