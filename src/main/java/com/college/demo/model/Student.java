package com.college.demo.model;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@Column(nullable = false, updatable = false)
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")

	private Long id;

	@NotBlank(message = "First Name is required")
	@Column(nullable = false)
	private String firstName;

	@NotBlank(message = "Last Name is required")
	@Column(nullable = false)
	private String lastName;

	@NotBlank(message = "Email is required")
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "Date of Birth is required")
	@Column(nullable = false)
	private LocalDate dob;

	@Transient
	private Integer age;

	public Integer getAge() {
		return Period.between(dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
