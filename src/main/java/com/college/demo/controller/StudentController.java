package com.college.demo.controller;

// import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import io.swagger.annotations.ApiOperation;
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
import com.college.demo.dto.StudentDTO;
import com.college.demo.mapper.StudentMapper;
import com.college.demo.model.Student;
import com.college.demo.service.StudentService;


@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentMapper studentMapper;
	
	@GetMapping("/{email}")
	@ApiOperation("Returns a student by email")
	public ResponseEntity<?> findStudentByEmail(@Valid @PathVariable("email") String email) {
		return ResponseEntity.ok().body(studentMapper.toStudentDTO(studentService.findStudentByEmail(email)));  // return 200, with JSON body
	}
	
	@GetMapping
	@ApiOperation("Returns all the Students")
	public ResponseEntity<?> findAllStudents() {
		return ResponseEntity.ok().body(studentMapper.toStudentDTOs(studentService.getAllStudents()));  // return 200, with JSON body
	}
	
	@PostMapping
	@ApiOperation("Add a new student")
	public ResponseEntity<?> addNewStudent(@Valid @RequestBody StudentDTO studentDTO) throws URISyntaxException {
		Student savedStudent = studentService.addNewStudent(studentMapper.toStudent(studentDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toStudentDTO(savedStudent));
		//return ResponseEntity.created(new URI("/api/v1/student/" + savedStudent.getId())).body(studentMapper.toStudentDTO(savedStudent));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Delete a Student")
	public ResponseEntity<?> deleteStudentById(@PathVariable("id") Long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.accepted().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Update a Student")
	public ResponseEntity<?> modifyStudent(
			@PathVariable("id") Long id,
			@RequestParam(name= "firstName", required = false) String firstName,
			@RequestParam(name= "lastName", required = false) String lastName,
			@RequestParam(name= "email", required = false) String email) {
		return ResponseEntity.ok().body(studentMapper.toStudentDTO(studentService.updateStudent(id, firstName, lastName, email)));
		
	}

}
