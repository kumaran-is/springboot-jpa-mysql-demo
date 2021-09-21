package com.college.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.college.demo.model.Enrollment;

public interface EnrollmentService {

	public List<Enrollment> getAllEnrollments();

	public List<Enrollment> findEnrollmentByStudent(Long studentId);
	
	public List<Enrollment> findEnrollmentByCourse(Long CourseId);

	public Enrollment enrollStudent(Enrollment enrollment);

	public void deleteEnrollmentByStudent(Long studentId);

	public Enrollment updateEnrollment(Long enrollmentId, String status, LocalDate endDate, Float score );
}
