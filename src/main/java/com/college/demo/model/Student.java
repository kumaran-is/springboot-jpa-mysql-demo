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
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Student Model")
public class Student {
	
	@Id
	@Column(nullable = false, updatable = false)
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator =  "student_sequence"
	)
	@ApiModelProperty(value ="Auto generated student Id", name="id")
	private Long id;
	
	@Column(nullable = false)
	@NotNull(message = "First Name cannot be null")
	@ApiModelProperty(value ="First Name of the student", name="name", required = true, example= "James")
	private String firstName;
	
	@Column(nullable = false)
	@NotNull(message = "Last Name cannot be null")
	@ApiModelProperty(value ="Last Name of the student", name="name", required = true, example= "Bond")
	private String lastName;
	
	@Column(nullable = false)
	@NotNull(message = "Email cannot be null")
	@ApiModelProperty(value ="Email id of the student", name="email", required = true, example= "james@gmail.com")
	private String email;
	
	@Column(nullable = false)
	@NotNull(message = "Date of Birth cannot be null")
	@ApiModelProperty(value ="Student Date of Birth in YYYY-MM-DD format", name="dob", required = true, example= "2000-01-25")
	private LocalDate dob;
	
	@Transient
	@ApiModelProperty(value ="System calculated age of the student based on the student DOB, never stored in the database", name="age")
	private Integer age;
	
	public Integer getAge() {
		return Period.between(dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
