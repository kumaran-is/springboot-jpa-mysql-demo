package com.college.demo.controller;

import java.net.URI;
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

import com.college.demo.exception.ResourceAlreadyExistsException;
import com.college.demo.exception.ResourceNotFoundException;
import com.college.demo.model.Student;
import com.college.demo.service.StudentService;
import com.college.demo.service.StudentServiceImpl;

@Slf4j
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/list")
	@ApiOperation("Returns all the Students")
	public ResponseEntity<List<Student>> getStudents() {
		return ResponseEntity.ok().body(studentService.getStudents());  // return 200, with json body
	}
	
	@PostMapping
	@ApiOperation("Add a new student")
	public ResponseEntity<?> addNewStudent(@Valid @RequestBody Student student) throws URISyntaxException {
		try {
			Student newStudent = studentService.addNewStudent(student);
			log.debug("Inside controller >>>> "+ newStudent);
			return ResponseEntity.created(new URI("/api/v1/student/" + newStudent.getId())).body(newStudent);
		} catch (ResourceAlreadyExistsException ex) {
			// log exception first, then return Conflict (409)
			log.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error Message");
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Delete a Student")
	public ResponseEntity<?> deleteStudentById(@PathVariable Long id) {
		try {
			studentService.deleteStudent(id);
			// return ResponseEntity.noContent().build() or use below for void response
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			// log exception first, then return Not Found (404)
			log.error("Inside Controller deleteStudentById >> " +ex.getMessage());
	        return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Update a Student")
	public ResponseEntity<?> modifyStudent(
			@PathVariable Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		try {
			return ResponseEntity.ok().body(studentService.updateStudent(id, name, email));
		}  catch (ResourceNotFoundException ex) {
	        // log exception first, then return Not Found (404)
			log.error("Inside Controller modifyStudent >> " +ex.getMessage());
	        return ResponseEntity.notFound().build();
	    } 
	}

}
