package com.college.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Student DTO")
public class StudentDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "First Name of the student", name = "firstName", required = true, example = "James")
	@NotNull(message = "First Name cannot be null")
	private String firstName;

	@ApiModelProperty(value = "Last Name of the student", name = "lastName", required = true, example = "Bond")
	@NotNull(message = "Last Name cannot be null")
	private String lastName;

	@ApiModelProperty(value = "Email id of the student", name = "email", required = true, example = "james@gmail.com")
	@NotNull(message = "Email cannot be null")
	private String email;

	@ApiModelProperty(value = "Student Date of Birth in YYYY-MM-DD format", name = "dob", required = true, example = "2000-01-25")
	@NotNull(message = "Date of Birth cannot be null")
	private LocalDate dob;

	@ApiModelProperty(value = "System calculated age of the student based on the student DOB, never stored in the database", name = "age")
	private Integer age;

}
