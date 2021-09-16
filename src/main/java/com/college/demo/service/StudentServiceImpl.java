package com.college.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.demo.model.Student;
import com.college.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	@Override
	public void addNewStudent(Student student) {
		
		Optional<Student>  studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		
		if(studentOptional.isPresent()) {
			log.error("Student with Email Id "+ student.getEmail()+" already taken");
			throw new IllegalStateException("Student with Email Id "+ student.getEmail()+" already taken");
		}
		
		studentRepository.save(student);
	}
	
	@Override
	public void deleteStudent(Long id) { 
		
		boolean exists = studentRepository.existsById(id);
		if(!exists) {
			log.error("Student with id " + id + " does not exists");
			throw new IllegalStateException("Student with id " + id + " does not exists");
		}
		
		studentRepository.deleteById(id);
	}
	
	@Transactional
	@Override
	public void updateStudent(Long id, String name, String email) { 
		
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> { 
					log.error("Student with id " + id + " does not exists");
					throw new IllegalStateException("Student with id " + id + " does not exists");
				 });
		
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(),  name)) {
			student.setName(name);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),  email)) {
			student.setEmail(email);
		}
	}
}
