package com.college.demo.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.college.demo.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enrollment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment extends AbstractEntity {
	
	@Column(name = "start_date", columnDefinition = "DATE", nullable = false)
	private LocalDate startDate;
	
	@Column(name = "end_date", columnDefinition = "DATE", nullable = false)
	private LocalDate endDate;
	
	@Column(name = "score", nullable = false)
	private Float score;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;
	
	@ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    Student student;

	@ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    Course course;

}
