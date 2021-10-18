package com.college.demo.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.college.demo.constants.Status;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "enrollment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude={"student", "course"})
@ToString(exclude = {"student", "course"})
@JsonIdentityInfo(
   generator = ObjectIdGenerators.PropertyGenerator.class,
   property = "id"
)
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
	
	@ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    Student student;
	
	@Column(name = "student_id")
	private Long studentId;

	@ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    Course course;
	
	@Column(name = "course_id")
	private Long courseId;

}
