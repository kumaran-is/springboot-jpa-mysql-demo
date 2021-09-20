package com.college.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "course_faculty",
		joinColumns = { @JoinColumn(name = "course_id")},
		inverseJoinColumns = { @JoinColumn (name = "faculty_id")})
	private Set<Faculty> Faculties = new HashSet<Faculty>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "course_id")
	private Set<Enrollment>  enrollments = new HashSet<Enrollment>();
}
