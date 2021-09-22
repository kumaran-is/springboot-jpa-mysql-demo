package com.college.demo.controller;

//import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
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
import com.college.demo.dto.EnrollmentDTO;
import com.college.demo.exception.InvalidInputException;
import com.college.demo.mapper.EnrollmentMapper;
import com.college.demo.model.Enrollment;
import com.college.demo.service.EnrollmentService;

@Slf4j
@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;
	@Autowired
	private EnrollmentMapper enrollmentMapper;
	
	@GetMapping
	@ApiOperation("Returns all the Enrollments")
	// public ResponseEntity<List<CEnrollmentDTO>> findAllEnrollments() {
	public ResponseEntity<?> findAllEnrollments() {
		// return 200, with JSON body
		List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
		// return ResponseEntity.ok().body(enrollmentMapper.toEnrollmentDTOs(enrollmentService.getAllEnrollments()));
		return ResponseEntity.ok().body(enrollments);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Returns an enrollment by an enrollment Id")
	public ResponseEntity<Enrollment> findEnrollmentByEnrollmentId(@Valid @PathVariable("id") Long id) {
		// return 200, with JSON body
		Enrollment enrollment = enrollmentService.findEnrollmentById(id);
		// return ResponseEntity.ok().body(enrollmentMapper.toEnrollmentDTO(enrollmentService.findEnrollmentById(id)));
		return ResponseEntity.ok().body(enrollment);
	}
	
	@GetMapping("/{studentId}")
	@ApiOperation("Returns all the enrollments by a student")
	public ResponseEntity<List<Enrollment>> findEnrollmentByStudent(@Valid @PathVariable("studentId") Long studentId) {
		// return 200, with JSON body
		List<Enrollment> enrollments = enrollmentService.findEnrollmentByStudent(studentId);
		// return ResponseEntity.ok().body(enrollmentMapper.toEnrollmentDTO(enrollmentService.findEnrollmentByStudent(studentId)));
		return ResponseEntity.ok().body(enrollments);
	}
	
	
	@GetMapping("/{courseId}")
	@ApiOperation("Returns all the enrollments by a course")
	public ResponseEntity<List<Enrollment>> findEnrollmentByCourse(@Valid @PathVariable("courseId") Long courseId) {
		// return 200, with JSON body
		List<Enrollment> enrollments = enrollmentService.findEnrollmentByCourse(courseId);
		// return ResponseEntity.ok().body(enrollmentMapper.toEnrollmentDTO(enrollmentService.findEnrollmentByCourse(courseId)));
		return ResponseEntity.ok().body(enrollments);
	}
	
	@GetMapping("/{status}")
	@ApiOperation("Returns all the enrollments by a status")
	public ResponseEntity<List<Enrollment>> findEnrollmentByStatus(@Valid @PathVariable("status") String status) {
		// return 200, with JSON body
		List<Enrollment> enrollments = enrollmentService.findEnrollmentByStatus(status);
		// return ResponseEntity.ok().body(enrollmentMapper.toEnrollmentDTO(enrollmentService.findEnrollmentByStatus(status)));
		return ResponseEntity.ok().body(enrollments);
	}
	
	@GetMapping("/{studentId}/{courseId}")
	@ApiOperation("Returns an enrollment by a student and a course")
	public ResponseEntity<Enrollment> findEnrollmentByStudentandCourse(@Valid @PathVariable("studentId") Long studentId, @Valid @PathVariable("courseId") Long courseId) {
		// return 200, with JSON body
		Enrollment enrollment = enrollmentService.findEnrollmentByStudentandCourse(studentId, courseId);
		// return ResponseEntity.ok().body(enrollmentMapper.toEnrollmentDTO(enrollmentService.findEnrollmentByStudentandCourse(studentId, courseId)));
		return ResponseEntity.ok().body(enrollment);
	}
	
	@PostMapping
	@ApiOperation("Enroll a student to a course")
	public ResponseEntity<EnrollmentDTO> enrollAStudent(@Valid @RequestBody EnrollmentDTO enrollmentDTO) throws URISyntaxException {
		log.debug("Controller>>>>>>>>>>>enrollAStudent>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + enrollmentDTO);
		
		log.debug("Controller>>>>>>>>>>>enrollmentMapper.toEnrollment(enrollmentDTO)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + enrollmentMapper.toEnrollment(enrollmentDTO));
		Enrollment savedEnrollment = enrollmentService.enrollStudent(enrollmentMapper.toEnrollment(enrollmentDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentMapper.toEnrollmentDTO(savedEnrollment));
		// return ResponseEntity.created(new URI("/api/v1/enrollment/" +
		// savedEnrollment.getId())).body(enrollmentMapper.toEnrollmentDTO(savedEnrollment));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete an Enrollment by an enrollment Id")
	public ResponseEntity<Void> deleteEnrollmentById(@PathVariable("id") Long id) {
		if (null == id || id.equals(0L)) {
			throw new InvalidInputException("702", "Id is not valid");
		}
		enrollmentService.deleteEnrollmentById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{studentId}")
	@ApiOperation("Delete one or more enrollments for a student")
	public ResponseEntity<Void> deleteEnrollmentByStudent(@PathVariable("studentId") Long studentId) {
		if (null == studentId || studentId.equals(0L)) {
			throw new InvalidInputException("702", "Student Id is not valid");
		}
		enrollmentService.deleteEnrollmentByStudent(studentId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{studentId}/{courseId}")
	@ApiOperation("Delete an enrollment by student and course")
	public ResponseEntity<Void> deleteEnrollmentByStudentandCourse(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
		if (null == studentId || studentId.equals(0L)) {
			throw new InvalidInputException("702", "Student Id is not valid");
		}
		if (null == courseId || courseId.equals(0L)) {
			throw new InvalidInputException("702", "Course Id is not valid");
		}
		enrollmentService.deleteEnrollmentByStudentAndCourse(studentId, courseId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Update Enrollment ")
	public ResponseEntity<Enrollment> updateEnrollment(@PathVariable("id") Long id,
			@RequestParam(name = "status", required = false) String status,
			@RequestParam(name = "endDate", required = false) LocalDate endDate,
			@RequestParam(name = "score", required = false) Float score) {
		if (null == id || id.equals(0L)) {
			throw new InvalidInputException("702", "Id is not valid");
		}
		Enrollment enrollment = enrollmentService.updateEnrollment(id, status, endDate, score);
		// return ResponseEntity.ok().body(enrollmentMapper.toEnrollmentDTO(enrollmentService.updateEnrollment(id, status, endDate, score)));
		return ResponseEntity.ok().body(enrollment);

	}
}
