package com.college.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.college.demo.audit.AuditableDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.college.demo.constants.Status;
import com.college.demo.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Enrollment DTO")
public class EnrollmentDTO extends AuditableDTO<String> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Course start date in YYYY-MM-DD format", name = "startDate", required = true, example = "2000-01-25")
	@NotNull(message = "Course start date cannot be null")
	@FutureOrPresent(message = "Course start date cannot be in the past")
	private LocalDate startDate;
	
	@ApiModelProperty(value = "Course end date in YYYY-MM-DD format", name = "endDate", required = true, example = "2000-01-25")
	@NotNull(message = "Course end date cannot be null")
	@FutureOrPresent(message = "Course end date cannot be in the past")
	private LocalDate endDate;
	
	@ApiModelProperty(value = "Student score should be in decimal format", name = "score", required = false, example = "4.5")
	@Max(value = 10, message = "Student score should not be greater than 10")
	private Float score;
	
	@ApiModelProperty(value = "Student course status", name = "status", required = true, example = "[ENROLLED, INPROGRESS, COMPLETE, WITHDRAWAL, CANCEL]")
	@NotNull(message = "Student course status cannot be null")
	private Status status;
	
	@ApiModelProperty(value = "Enrollment's student information", name = "studentDTO", required = false)
	@JsonIgnoreProperties(value = {"studentDTO", "hibernateLazyInitializer"})
	StudentDTO student;
	
	@ApiModelProperty(value = "Enrollment's course information", name = "courseDTO", required = false)
	@JsonIgnoreProperties(value = {"courseDTO", "hibernateLazyInitializer"})
	CourseDTO course;
	
	@ApiModelProperty(value = "Student Id in numeric Long data type", name = "studentId", required = true, example = "1")
	private Long studentId;
	
	@ApiModelProperty(value = "Course Id in numeric Long data type", name = "courseId", required = true, example = "1")
	private Long courseId;

}
