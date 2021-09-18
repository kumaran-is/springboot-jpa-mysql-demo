package com.college.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.demo.exception.BusinessException;
import com.college.demo.exception.ResourceAlreadyExistsException;
import com.college.demo.exception.ResourceNotFoundException;
import com.college.demo.model.Student;
import com.college.demo.repository.StudentRepository;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	@Override
	public Student findStudentByEmail(String email)  {
		
		try
		{
			return studentRepository.findStudentByEmail(email)
				.orElseThrow(() -> { 
					throw new ResourceNotFoundException("700", "Student with email " + email + " does not exists");
				 });
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw new BusinessException("600", "Email is null" + e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException("601", "Something went wrong in Service layer while fetching student by email" + e.getMessage(), e);
		}	
	}
	
	@Override
	public List<Student> getAllStudents() {
		try
		{
			return studentRepository.findAll();
		} catch (Exception e) {
			throw new BusinessException("602","Something went wrong in Service layer while fetching all students" + e.getMessage(), e);
		}		
	}
	
	
	@Override
	public Student addNewStudent(Student student) {
		try
		{
			Optional<Student>  studentOptional = studentRepository.findStudentByEmail(student.getEmail());
			
			if(studentOptional.isPresent()) {
				throw new ResourceAlreadyExistsException("701", "Student with Email Id "+ student.getEmail() +" already taken");
			}
			
			return studentRepository.save(student);
		} catch (ResourceAlreadyExistsException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw new BusinessException("603", "given student is null" + e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException("604","Something went wrong in Service layer while saving Student" + e.getMessage(), e);
		}
	}
	

	@Override
	public void deleteStudent(Long id) { 
		
		try 
		{
			boolean exists = studentRepository.existsById(id);
			if(!exists) {
				throw new ResourceNotFoundException("700", "Student with id " + id + " does not exists");
			}
			
			studentRepository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw new BusinessException("603", "student id is null" + e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException("605","Something went wrong in Service layer while deleting Student" + e.getMessage(), e);
		}	
	}
	
	
	@Override
	public Student updateStudent(Long id, String firstName, String lastName, String email) { 
		try 
		{
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> { 
						throw new ResourceNotFoundException("700", "Student with id " + id + " does not exists");
					 });
			
			if(firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(),  firstName)) {
				student.setFirstName(firstName);
			}
			
			if(lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(),  lastName)) {
				student.setLastName(lastName);
			}
			
			if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),  email)) {
				student.setEmail(email);
			}
			return studentRepository.save(student);
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("606","Something went wrong in Service layer while updating Student" + e.getMessage(), e);
		}	
	}
}
