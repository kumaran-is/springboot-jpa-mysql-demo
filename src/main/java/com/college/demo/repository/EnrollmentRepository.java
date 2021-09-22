package com.college.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.college.demo.model.Enrollment;
import com.college.demo.constants.Status;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	@Transactional(readOnly = true)
	@Query("SELECT e FROM Enrollment e JOIN e.student s WHERE s.id = ?1")
	List<Enrollment> findEnrollmentByStudent(Long studentId);
	
	@Transactional(readOnly = true)
	@Query("SELECT e FROM Enrollment e JOIN e.course c WHERE c.id = ?1")
	List<Enrollment> findEnrollmentByCourse(Long courseId);
	
	@Transactional(readOnly = true)
	@Query("SELECT e FROM Enrollment e WHERE e.status = ?1")
	List<Enrollment> findByStatus(Status status);
	
	@Transactional(readOnly = true)
	//@Query("SELECT e FROM Enrollment e JOIN e.student s JOIN e.course c WHERE s.id = ?1 and c.id = ?2")
	@Query("SELECT e FROM Enrollment e WHERE e.studentId = ?1 and e.courseId = ?2")
	Optional<Enrollment>  findEnrollmentByStudentAndCourse(Long studentId, Long courseId);
	
	@Transactional(readOnly = true)
	 @Query("SELECT case when count(e)> 0 then true else false end from Enrollment e WHERE e.studentId = ?1")
    Boolean existsByStudent(Long studentId);
	
	@Transactional(readOnly = true)
	@Query("SELECT case when count(e)> 0 then true else false end from Enrollment e WHERE e.courseId = ?1")
	Boolean  existsByCourse(Long courseId);
	
	@Transactional(readOnly = true)
	@Query("SELECT case when count(e)> 0 then true else false end from Enrollment e WHERE e.status = ?1")
	Boolean existsByStatus(Status status);
	
	@Transactional(readOnly = true)
	@Query("SELECT case when count(e)> 0 then true else false end from Enrollment e WHERE e.studentId = ?1 and e.courseId = ?2")
	Boolean existsByStudentAndCourse(Long studentId, Long courseId);
	
	@Modifying
	@Query("DELETE FROM Enrollment e WHERE e.studentId = ?1")
	void deleteByStudent(Long studentId);

	@Modifying
	@Query("DELETE FROM Enrollment e WHERE e.courseId = ?1")
	void deleteByCourse(Long courseId);

	@Modifying
	@Query("DELETE FROM Enrollment e WHERE e.status = ?1")
	void deleteByStatus(Status status);
	
	@Modifying
	@Query("DELETE FROM Enrollment e WHERE e.studentId = ?1 and e.courseId = ?2")
	void deleteByStudentandCourse(Long studentId, Long courseId);
	
}

