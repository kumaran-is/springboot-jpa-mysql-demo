package com.college.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.college.demo.model.Enrollment;

public interface EnrollmentService {

	public List<Enrollment> getAllEnrollments();
	
	public Enrollment findEnrollmentById(Long id);

	public List<Enrollment> findEnrollmentByStudent(Long studentId);
	
	public List<Enrollment> findEnrollmentByCourse(Long courseId);
	
	public List<Enrollment> findEnrollmentByStatus(String status);
	
	public Enrollment findEnrollmentByStudentandCourse(Long studentId, Long courseId);

	public Enrollment enrollStudent(Enrollment enrollment);
	
	public void deleteEnrollmentById(Long enrollmentId);

	public void deleteEnrollmentByStudent(Long studentId);
	
	public void deleteEnrollmentByCourse(Long courseId);
	
	public void deleteEnrollmentByStatus(String status);
	
	public void deleteEnrollmentByStudentAndCourse(Long studentId, Long courseId);

	public Enrollment updateEnrollment(Long enrollmentId, String status, LocalDate endDate, Float score );
}
