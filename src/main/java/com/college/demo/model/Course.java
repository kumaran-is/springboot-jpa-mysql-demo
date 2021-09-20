package com.college.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course extends AbstractEntity {
	
	@Column(name = "course_name", nullable = false)
	private String courseName;
	
	@Column(name = "duration", nullable = false)
	private Integer duration;
}
