package com.college.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private LocalDate dob;
	private Integer age;
		
}
