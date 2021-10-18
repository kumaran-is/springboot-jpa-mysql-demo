package com.college.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.demo.exception.ResourceAlreadyExistsException;
import com.college.demo.exception.ResourceNotFoundException;
import com.college.demo.repository.FacultyRepository;
import com.college.demo.model.Faculty;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Faculty> getAllFaculties() {
		return facultyRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Faculty findFacultyByEmail(String email) {
		return facultyRepository.findFacultyByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("700", "Faculty with email " + email + " does not exists"));
	}

	@Override
	public Faculty addNewFaculty(Faculty faculty) {
	
		Optional<Faculty> facultyOptional = facultyRepository.findFacultyByEmail(faculty.getEmail());

		if (facultyOptional.isPresent()) {
			throw new ResourceAlreadyExistsException("701",
					"Faculty with Email Id " + faculty.getEmail() + " already taken");
		}

		return facultyRepository.save(faculty);
	}

	@Override
	public void deleteFaculty(Long id) {
		boolean exists = facultyRepository.existsById(id);
		if (!exists) {
			throw new ResourceNotFoundException("700", "Faculty with id " + id + " does not exists");
		}

		facultyRepository.deleteById(id);
	}

	@Override
	public Faculty updateFaculty(Long id, String firstName, String lastName, String email) {
		Faculty faculty = facultyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("700", "Faculty with id " + id + " does not exists"));

		if (firstName != null && firstName.length() > 0 && !Objects.equals(faculty.getFirstName(), firstName)) {
			faculty.setFirstName(firstName);
		}

		if (lastName != null && lastName.length() > 0 && !Objects.equals(faculty.getLastName(), lastName)) {
			faculty.setLastName(lastName);
		}

		if (email != null && email.length() > 0 && !Objects.equals(faculty.getEmail(), email)) {
			faculty.setEmail(email);
		}
		return facultyRepository.save(faculty);
	}

}
