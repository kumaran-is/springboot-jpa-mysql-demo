package com.college.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_contact_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(exclude = {"student"})
// @ToString(exclude = {"student"})
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
	

	/*@JsonIgnoreProperties(value = {"studentContactInfo", "hibernateLazyInitializer"})
	@OneToOne(targetEntity = StudentContactInfo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "studentContactInfo")
	private Student student;  */
}
