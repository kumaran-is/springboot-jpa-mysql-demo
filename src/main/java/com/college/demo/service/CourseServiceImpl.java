package com.college.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.demo.exception.ResourceAlreadyExistsException;
import com.college.demo.exception.ResourceNotFoundException;
import com.college.demo.model.Course;
import com.college.demo.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Course> getAllCourses() {

		return courseRepository.findAll();
	}

	@Override
	public Course findCourseById(Long id) {
		return courseRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("700", "Course with id " + id + " does not exists"));
		
	}
	
	@Override
	public Course findCourseByCourseName(String courseName) {
		return courseRepository.findCourseByCourseName(courseName).orElseThrow(
				() -> new ResourceNotFoundException("700", "Course name " + courseName + " does not exists"));
		
	}

	@Override
	public Course addNewCourse(Course course) {
		log.debug("addNewCourse service course ....." + course);

		Optional<Course> CourseOptional = courseRepository.findCourseByCourseName(course.getCourseName());

		if (CourseOptional.isPresent()) {
			throw new ResourceAlreadyExistsException("701",
					"Course with name " + course.getCourseName() + " already exists");
		}

		return courseRepository.save(course);	
	}

	@Override
	public void deleteCourse(Long id) {
		boolean exists = courseRepository.existsById(id);
		if (!exists) {
			throw new ResourceNotFoundException("700", "Course with id " + id + " does not exists");
		}

		courseRepository.deleteById(id);

	}

	@Override
	public Course updateCourseDuration(Long id, Integer duration) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("700", "Course with id " + id + " does not exists"));

		if (duration != null && !Objects.equals(course.getDuration(), duration)) {
			course.setDuration(duration);
		}

		return courseRepository.save(course);
	}

}
