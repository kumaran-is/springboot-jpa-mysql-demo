package com.college.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import com.college.demo.audit.AuditableDTO;
import com.college.demo.constants.Gender;
import com.college.demo.model.Enrollment;
import com.college.demo.model.Student;
import com.college.demo.model.StudentContactInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Student DTO")
@EqualsAndHashCode(exclude = {"studentContactInfo", "enrollments"})
@ToString(exclude = {"studentContactInfo", "enrollments"})
public class StudentDTO extends AuditableDTO<String> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "First name of the student", name = "firstName", required = true, example = "James")
	@NotNull(message = "Student's first name cannot be null")
	@NotBlank(message = "Student's first name is required")
	@Size(min = 2, message = "Student's first name should have at least 2 characters")
	private String firstName;

	@ApiModelProperty(value = "Last name of the student", name = "lastName", required = true, example = "Bond")
	@NotNull(message = "Student's fast name cannot be null")
	@NotBlank(message = "Student's fast name is required")
	@Size(min = 2, message = "Student's fast name should have at least 2 characters")
	private String lastName;

	@ApiModelProperty(value = "Email id of the student", name = "email", required = true, example = "james@gmail.com")
	@NotNull(message = "Student's email cannot be null")
	@NotBlank(message = "Student's email is required")
	@Email(message = "Student's email should be a valid email format")
	private String email;

	@ApiModelProperty(value = "Student's date of birth in YYYY-MM-DD format", name = "dob", required = true, example = "2000-01-25")
	@NotNull(message = "Student's date of birth cannot be null")
	@Past(message = "Student's date of birth cannot be in the present or in the future")
	private LocalDate dob;
	
	@ApiModelProperty(value = "Student's gender", name = "gender", required = true, example = "[MALE, FEMALE]")
	@NotNull(message = "Student's gender cannot be null")
	private Gender gender;

	@ApiModelProperty(value = "System calculated age of the student based on the student DOB, never stored in the database", name = "age")
	private Integer age;
	
	@ApiModelProperty(value = "Student's contact information", name = "studentContactInfo")
	@JsonIgnoreProperties(value = {"student", "hibernateLazyInitializer"})
	private StudentContactInfoDTO studentContactInfo;
	
	@ApiModelProperty(value = "Set of enrollments(courses) enrolled by a student")
	@JsonIgnoreProperties
	private Set<EnrollmentDTO> enrollments = new HashSet<EnrollmentDTO>();

}
