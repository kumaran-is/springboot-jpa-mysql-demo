package com.college.demo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import com.college.demo.audit.AuditableDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Course DTO")
public class CourseDTO extends AuditableDTO<String> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Course name", name = "courseName", required = true, example = "Java Microservices")
	@NotNull(message = "Course name cannot be null")
	@NotBlank(message = "Course name is required")
	@Size(min = 2, message = "Course name should have at least 2 characters")
	private String courseName;
	
	@ApiModelProperty(value = "Course duration in hours", name = "duration", required = true, example = "4")
	@NotNull(message = "Course duration cannot be null")
	@NotBlank(message = "Course duration is required")
	@Positive(message = "Course duration should be positive integer")
	private Integer duration;
	
	@ApiModelProperty(value = "Set of faculties assigned to a course")
	private Set<FacultyDTO> Faculties = new HashSet<FacultyDTO>();
	
	@ApiModelProperty(value = "Set of enrollments for a course")
	private Set<EnrollmentDTO>  enrollments = new HashSet<EnrollmentDTO>();
	
}
