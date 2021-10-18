package com.college.demo.controller;

// import java.net.URI;
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

import com.college.demo.aop.LoggingAspect;
import com.college.demo.dto.StudentDTO;
import com.college.demo.exception.InvalidInputException;
import com.college.demo.mapper.StudentMapper;
import com.college.demo.model.Student;
import com.college.demo.service.StudentService;
import com.college.demo.util.CommonUtils;

@Slf4j
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentMapper studentMapper;

	@GetMapping("/{email}")
	@ApiOperation("Returns a student by email")
	public ResponseEntity<Student> findStudentByEmail(@Valid @PathVariable("email") String email) {
		if (CommonUtils.isBlankString(email)) {
			throw new InvalidInputException("703", "Email is null or blank");
		}
		// return 200, with JSON body
		// return ResponseEntity.ok().body(studentMapper.toStudentDTO(studentService.findStudentByEmail(email)));
		return ResponseEntity.ok().body(studentService.findStudentByEmail(email));
	}

	@GetMapping
	@ApiOperation("Returns all the Students")
	public ResponseEntity<List<Student>> findAllStudents() {
		// return 200, with JSON body
		// return ResponseEntity.ok().body(studentMapper.toStudentDTOs(studentService.getAllStudents()));
		return ResponseEntity.ok().body(studentService.getAllStudents());
	}

	@PostMapping
	@ApiOperation("Add a new student")
	public ResponseEntity<StudentDTO> addNewStudent(@Valid @RequestBody StudentDTO studentDTO) throws URISyntaxException {

		Student savedStudent = studentService.addNewStudent(studentMapper.toStudent(studentDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toStudentDTO(savedStudent));
		// return ResponseEntity.created(new URI("/api/v1/student/" +
		// savedStudent.getId())).body(studentMapper.toStudentDTO(savedStudent));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete a Student")
	public ResponseEntity<Void> deleteStudentById(@PathVariable("id") Long id) {
		if (null == id || id.equals(0L)) {
			throw new InvalidInputException("702", "Id is not valid");
		}
		studentService.deleteStudent(id);
		// The request has been accepted for processing, but the processing has not been
		// completed
		// return ResponseEntity.accepted().build();
		// no content to return
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@ApiOperation("Update a Student")
	public ResponseEntity<StudentDTO> modifyStudent(@PathVariable("id") Long id,
			@RequestParam(name = "firstName", required = false) String firstName,
			@RequestParam(name = "lastName", required = false) String lastName,
			@RequestParam(name = "email", required = false) String email) {
		if (null == id || id.equals(0L)) {
			throw new InvalidInputException("702", "Id is not valid");
		}
		return ResponseEntity.ok()
				.body(studentMapper.toStudentDTO(studentService.updateStudent(id, firstName, lastName, email)));

	}

}
