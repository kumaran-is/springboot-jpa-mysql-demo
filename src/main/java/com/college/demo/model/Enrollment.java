package com.college.demo.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enrollment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment extends AbstractEntity {
	
	@Column(columnDefinition = "DATE", nullable = false)
	private LocalDate startDate;
	
	@Column(columnDefinition = "DATE", nullable = false)
	private LocalDate endDate;
	
	private Float score;
	
	@Column(nullable = false)
	private String status;

}
