package com.college.demo.model;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.college.demo.model.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude={"faculties"})
@ToString(exclude = {"faculties"})
@JsonIdentityInfo(
   generator = ObjectIdGenerators.PropertyGenerator.class,
   property = "id"
)
public class Course extends AbstractEntity {
	
	@Column(name = "course_name", nullable = false)
	private String courseName;
	
	@Column(name = "duration", nullable = false)
	private Integer duration;
	
	@JsonIgnoreProperties(value = {"course", "hibernateLazyInitializer"})
	@ManyToMany(targetEntity = Faculty.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "course_faculty",
		joinColumns = { @JoinColumn(name = "course_id", referencedColumnName="id")},
		inverseJoinColumns = { @JoinColumn (name = "faculty_id", referencedColumnName="id")})
	 private List<Faculty>  faculties;
	//private Set<Faculty> faculties = new HashSet<Faculty>();
	
	/*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "course_id")
	private Set<Enrollment>  enrollments = new HashSet<Enrollment>();  */
}
