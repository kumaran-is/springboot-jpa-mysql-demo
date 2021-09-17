package com.college.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.demo.exception.ResourceAlreadyExistsException;
import com.college.demo.exception.ResourceNotFoundException;
import com.college.demo.model.Student;
import com.college.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	@Override
	public Student addNewStudent(Student student) throws ResourceAlreadyExistsException {
		
		Optional<Student>  studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		
		if(studentOptional.isPresent()) {
			log.error("Student with Email Id "+ student.getEmail()+" already taken");
			throw new ResourceAlreadyExistsException("Student with Email Id \"+ student.getEmail()+\" already taken");
		}
		
		return studentRepository.save(student);
	}
	
	@Override
	public void deleteStudent(Long id) throws ResourceNotFoundException { 
		
		boolean exists = studentRepository.existsById(id);
		if(!exists) {
			log.error("Student with id " + id + " does not exists");
			throw new ResourceNotFoundException("Student with id " + id + " does not exists");
		}
		
		studentRepository.deleteById(id);
	}
	
	
	@Override
	public Student updateStudent(Long id, String name, String email)  throws ResourceNotFoundException { 
		
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> { 
					log.error("Student with id " + id + " does not exists");
					throw new ResourceNotFoundException("Student with id " + id + " does not exists");
				 });
		
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(),  name)) {
			student.setName(name);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),  email)) {
			student.setEmail(email);
		}
		return studentRepository.save(student);
		
	}
}
