package com.college.demo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(description = "Faculty DTO")
public class FacultyDTO extends AuditableDTO<String> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "First name of the faculty", name = "firstName", required = true, example = "James")
	@NotNull(message = "Faculty's first Name cannot be null")
	@NotBlank(message = "Faculty's first Name is required")
	@Size(min = 2, message = "Faculty's first Name should have at least 2 characters")
	private String firstName;

	@ApiModelProperty(value = "Last name of the faculty", name = "lastName", required = true, example = "Bond")
	@NotNull(message = "Faculty's last name cannot be null")
	@NotBlank(message = "Faculty's last name is required")
	@Size(min = 2, message = "Faculty's last name should have at least 2 characters")
	private String lastName;

	@ApiModelProperty(value = "Email id of the faculty", name = "email", required = true, example = "james@gmail.com")
	@NotNull(message = "Faculty's email cannot be null")
	@NotBlank(message = "Faculty's email is required")
	@Email(message = "Faculty's email should be a valid email format")
	private String email;
	
	@ApiModelProperty(value = "Set of courses assigned to a faculty")
	private Set<CourseDTO> Courses = new HashSet<CourseDTO>();
	

}
