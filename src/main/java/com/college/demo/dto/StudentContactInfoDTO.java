package com.college.demo.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.college.demo.audit.AuditableDTO;
import io.swagger.annotations.ApiModelProperty;

public class StudentContactInfoDTO extends AuditableDTO<String> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Phone number of the student", name = "phone", required = true, example = "407-724-3091")
	@NotNull(message = "Phone cannot be null")
	@NotBlank(message = "Phone is required")
	private String phone;
	
	@ApiModelProperty(value = "Street address of the student", name = "street", required = true, example = "5269 International Dr")
	@NotNull(message = "Street cannot be null")
	@NotBlank(message = "Street is required")
    private String street;
	
	@ApiModelProperty(value = "City of the student", name = "city", required = true, example = "Orlando")
	@NotNull(message = "City cannot be null")
	@NotBlank(message = "City is required")
    private String city;
	
	@ApiModelProperty(value = "State of the student", name = "state", required = true, example = "Florida")
	@NotNull(message = "State cannot be null")
	@NotBlank(message = "State is required")
    private String state;
	
	@ApiModelProperty(value = "Zipcode of the student", name = "zipcode", required = true, example = "32819")
	@NotNull(message = "Zipcode cannot be null")
	@NotBlank(message = "Zipcode is required")
    private String zipcode;
	
	@ApiModelProperty(value = "Student's basic detail", name = "studentDTO", required = true)
	@NotNull(message = "Student cannot be null")
	@NotBlank(message = "Student is required")
	private StudentDTO student;

}
