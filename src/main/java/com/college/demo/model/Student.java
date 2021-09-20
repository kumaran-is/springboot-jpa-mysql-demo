package com.college.demo.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.college.demo.constants.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper=false, exclude={"studentContactInfo", "enrollments"})
//@ToString(exclude = {"studentContactInfo", "enrollments"})
public class Student extends AbstractEntity{

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "dob", columnDefinition = "DATE", nullable = false)
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private Gender gender;
	
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_contact_info_id")
	private StudentContactInfo studentContactInfo;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "student_id")
	private Set<Enrollment>  enrollments = new HashSet<Enrollment>();

	@Transient
	private Integer age;

	public Integer getAge() {
		return Period.between(dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
