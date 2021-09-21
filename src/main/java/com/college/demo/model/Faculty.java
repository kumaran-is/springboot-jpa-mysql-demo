package com.college.demo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.college.demo.model.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "faculty")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude={"courses"})
@ToString(exclude = {"courses"})
public class Faculty extends AbstractEntity {

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@JsonIgnoreProperties(value = {"faculty", "hibernateLazyInitializer"})
	@ManyToMany(targetEntity = Course.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "faculties")
	private List<Course> courses;
	// private Set<Course> courses = new HashSet<>();

}
