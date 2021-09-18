package com.college.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@NotBlank(message = "First Name is required")
	@Size(min = 2, message = "First Name should have at least 2 characters")
	private String firstName;

	@ApiModelProperty(value = "Last Name of the student", name = "lastName", required = true, example = "Bond")
	@NotNull(message = "Last Name cannot be null")
	@NotBlank(message = "Last Name is required")
	@Size(min = 2, message = "Last Name should have at least 2 characters")
	private String lastName;

	@ApiModelProperty(value = "Email id of the student", name = "email", required = true, example = "james@gmail.com")
	@NotNull(message = "Email cannot be null")
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be a valid email format")
	private String email;

	@ApiModelProperty(value = "Student Date of Birth in YYYY-MM-DD format", name = "dob", required = true, example = "2000-01-25")
	@NotNull(message = "Date of Birth cannot be null")
	@Past(message = "Date of Birth cannot be in the present or in the future")
	private LocalDate dob;

	@ApiModelProperty(value = "System calculated age of the student based on the student DOB, never stored in the database", name = "age")
	private Integer age;

}
