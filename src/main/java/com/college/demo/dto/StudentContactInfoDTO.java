package com.college.demo.dto;

import java.io.Serializable;
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
@ApiModel(description = "StudentContactInfo DTO")
//@EqualsAndHashCode(exclude = {"student"})
//@ToString(exclude = {"student"})
public class StudentContactInfoDTO extends AuditableDTO<String> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Phone number of the student", name = "phone", required = true, example = "407-724-3091")
	@NotNull(message = "Phone cannot be null")
	@NotBlank(message = "Phone is required")
	@Size(min = 10, message = "Phone number should have at least 10 characters")
	@Size(max = 15, message = "Phone number should not be greater than 25 characters")
	private String phone;
	
	@ApiModelProperty(value = "Street address of the student", name = "street", required = true, example = "5269 International Dr")
	@NotNull(message = "Street cannot be null")
	@NotBlank(message = "Street is required")
	@Size(max = 150, message = "Street address should not be greater than 150 characters")
    private String street;
	
	@ApiModelProperty(value = "City of the student", name = "city", required = true, example = "Orlando")
	@NotNull(message = "City cannot be null")
	@NotBlank(message = "City is required")
	@Size(max = 20, message = "City name should not be greater than 20 characters")
    private String city;
	
	@ApiModelProperty(value = "State of the student", name = "state", required = true, example = "Florida")
	@NotNull(message = "State cannot be null")
	@NotBlank(message = "State is required")
	@Size(max = 15, message = "State name should not be greater than 15 characters")
    private String state;
	
	@ApiModelProperty(value = "Zipcode of the student", name = "zipcode", required = true, example = "32819")
	@NotNull(message = "Zipcode cannot be null")
	@NotBlank(message = "Zipcode is required")
	@Size(max = 10, message = "Zipcode should not be greater than 15 characters")
    private String zipcode;
	
	/*@ApiModelProperty(value = "Student's basic detail", name = "studentDTO", required = true)
	@NotNull(message = "Student cannot be null")
	@NotBlank(message = "Student is required")
	@JsonIgnoreProperties(value = {"studentContactInfo", "hibernateLazyInitializer"})
	private StudentDTO student; */

}
