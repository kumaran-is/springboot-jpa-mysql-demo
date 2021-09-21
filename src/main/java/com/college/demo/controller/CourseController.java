package com.college.demo.controller;

//import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.college.demo.dto.CourseDTO;
import com.college.demo.exception.InvalidInputException;
import com.college.demo.mapper.CourseMapper;
import com.college.demo.model.Course;
import com.college.demo.service.CourseService;

@Slf4j
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseMapper courseMapper;

	
	@GetMapping
	@ApiOperation("Returns all the Courses")
	public ResponseEntity<List<CourseDTO>> findAllCourses() {
		// return 200, with JSON body
		return ResponseEntity.ok().body(courseMapper.toCourseDTOs(courseService.getAllCourses()));
	}
	
	@GetMapping("/{name}")
	@ApiOperation("Returns a course by name")
	public ResponseEntity<CourseDTO> findCourseByName(@Valid @PathVariable("name") String name) {
		// return 200, with JSON body
		return ResponseEntity.ok().body(courseMapper.toCourseDTO(courseService.findCourseByCourseName(name)));
	}

	@PostMapping
	@ApiOperation("Add a new Course")
	public ResponseEntity<CourseDTO> addNewCourse(@Valid @RequestBody CourseDTO courseDTO) throws URISyntaxException {
		
		log.debug("addNewCourse request courseDTO ....." + courseDTO);
		log.debug("addNewCourse CourseMapper.toCourse(studentDTO)>>>>>>> " + courseMapper.toCourse(courseDTO));
		Course savedCourse =courseService.addNewCourse(courseMapper.toCourse(courseDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(courseMapper.toCourseDTO(savedCourse));
		// return ResponseEntity.created(new URI("/api/v1/course/" +
		// savedCourse.getId())).body(courseMapper.toCourseDTO(savedCourse));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete a Course")
	public ResponseEntity<Void> deleteCourseById(@PathVariable("id") Long id) {
		if (null == id || id.equals(0L)) {
			throw new InvalidInputException("702", "Id is not valid");
		}
		courseService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Modify course duration")
	public ResponseEntity<CourseDTO> modifyCourseDuration(@PathVariable("id") Long id,
			@Valid @RequestParam(name = "duration", required = true) Integer duration) {
		if (null == id || id.equals(0L)) {
			throw new InvalidInputException("702", "Id is not valid");
		}
		return ResponseEntity.ok()
				.body(courseMapper.toCourseDTO(courseService.updateCourseDuration(id, duration)));

	}

}
