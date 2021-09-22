package com.college.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.college.demo.model.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	@Transactional(readOnly = true)
	// @Query("SELECT c FROM Course c where c.courseName = ?1")
	List<Enrollment> findEnrollmentByStudent(Long studentId);
	
	@Transactional(readOnly = true)
	// @Query("SELECT c FROM Enrollment c where c.courseName = ?1")
	List<Enrollment> findEnrollmentByCourse(Long courseId);
}

