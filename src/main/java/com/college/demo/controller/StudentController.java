package com.college.demo.controller;

import java.util.List;
import javax.validation.Valid;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.demo.model.Student;
import com.college.demo.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	
	@GetMapping("/list")
	@ApiOperation("Returns all the Students")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping
	@ApiOperation("Add a new student")
	public void addNewStudent(@Valid @RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	

	@DeleteMapping("/{id}")
	@ApiOperation("Delete a Student")
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Update a Student")
	public void modifyStudent(
			@PathVariable Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		studentService.updateStudent(id, name, email);
	}

}
