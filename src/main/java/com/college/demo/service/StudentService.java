package com.college.demo.service;

import java.util.List;
import com.college.demo.model.Student;

public interface StudentService {
	
	public List<Student> getAllStudents();
	
	public Student findStudentByEmail(String email);
	
	public Student addNewStudent(Student student);
	
	public void deleteStudent(Long id);
	
	public Student updateStudent(Long id, String name, String email);
}
