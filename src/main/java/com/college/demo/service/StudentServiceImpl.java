package com.college.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.demo.controller.StudentController;
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
	public Student findStudentByEmail(String email) {

		return studentRepository.findStudentByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("700", "Student with email " + email + " does not exists"));

	}

	@Override
	@Transactional(readOnly = true)
	public List<Student> getAllStudents() {

		return studentRepository.findAll();
	}

	@Override
	public Student addNewStudent(Student student) {
		log.debug("addNewStudent service student ....." + student);

		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

		if (studentOptional.isPresent()) {
			throw new ResourceAlreadyExistsException("701",
					"Student with Email Id " + student.getEmail() + " already taken");
		}

		return studentRepository.save(student);
		
	}

	@Override
	public void deleteStudent(Long id) {

		boolean exists = studentRepository.existsById(id);
		if (!exists) {
			throw new ResourceNotFoundException("700", "Student with id " + id + " does not exists");
		}

		studentRepository.deleteById(id);

	}

	@Override
	public Student updateStudent(Long id, String firstName, String lastName, String email) {

		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("700", "Student with id " + id + " does not exists"));

		if (firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(), firstName)) {
			student.setFirstName(firstName);
		}

		if (lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(), lastName)) {
			student.setLastName(lastName);
		}

		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			student.setEmail(email);
		}
		return studentRepository.save(student);

	}
}
