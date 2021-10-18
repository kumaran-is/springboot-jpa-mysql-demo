package com.college.demo.service;

import java.util.List;
import com.college.demo.model.Course;

public interface CourseService {
	public List<Course> getAllCourses();

	public Course findCourseById(Long id);
	
	public Course findCourseByCourseName(String courseName);

	public Course addNewCourse(Course course);

	public void deleteCourse(Long id);

	public Course updateCourseDuration(Long id, Integer duration);
}
