package com.college.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;
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


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Enrollment DTO")
public class EnrollmentDTO extends AuditableDTO<String> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Course start date in YYYY-MM-DD format", name = "startDate", required = true, example = "2000-01-25")
	@NotNull(message = "Course start date cannot be null")
	@NotBlank(message = "Course start date is required")
	@FutureOrPresent(message = "Course start date cannot be in the past")
	private LocalDate startDate;
	
	@ApiModelProperty(value = "Course end date in YYYY-MM-DD format", name = "endDate", required = true, example = "2000-01-25")
	@NotNull(message = "Course end date cannot be null")
	@NotBlank(message = "Course end date is required")
	@FutureOrPresent(message = "Course end date cannot be in the past")
	private LocalDate endDate;
	
	@ApiModelProperty(value = "Student score should be in decimal format", name = "score", required = false, example = "4.5")
	@Max(value = 10, message = "Student score should not be greater than 10")
	private Float score;
	
	@ApiModelProperty(value = "Student course status", name = "status", required = true, example = "[ENROLLED, INPROGRESS, COMPLETE, WITHDRAWAL, CANCEL]")
	@NotNull(message = "Student course status cannot be null")
	@NotBlank(message = "Student course status is required")
	private Status status;

}
