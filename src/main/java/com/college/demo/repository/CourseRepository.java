package com.college.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.college.demo.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Transactional(readOnly = true)
	// @Query("SELECT c FROM Course c where c.courseName = ?1")
	Optional<Course> findCourseByCourseName(String coursename);
}
