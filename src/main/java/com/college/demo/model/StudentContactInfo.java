package com.college.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_contact_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude = {"student"})
@ToString(exclude = {"student"})
public class StudentContactInfo extends AbstractEntity {

	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name = "street", nullable = false)
    private String street;
	
	@Column(name = "city", nullable = false)
    private String city;
	
	@Column(name = "state", nullable = false)
    private String state;
	
	@Column(name = "zipcode", nullable = false)
    private String zipcode;
	

	@OneToOne(targetEntity = Student.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "studentContactInfo")
	private Student student; 
	
	@OneToOne(targetEntity = StudentContactInfo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_contact_info_id")
	private StudentContactInfo studentContactInfo;
}
