package com.college.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.college.demo.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


}
