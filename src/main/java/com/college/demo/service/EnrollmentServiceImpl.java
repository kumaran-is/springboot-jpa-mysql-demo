package com.college.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.college.demo.exception.ResourceAlreadyExistsException;
import com.college.demo.exception.ResourceNotFoundException;
import com.college.demo.model.Enrollment;
import com.college.demo.repository.EnrollmentRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Enrollment> getAllEnrollments() {
		return enrollmentRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Enrollment findEnrollmentById(Long id) {
		return enrollmentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("700", "Enrollment with id " + id + " does not exists"));
	}


	@Override
	@Transactional(readOnly = true)
	public List<Enrollment> findEnrollmentByStudent(Long studentId) {
		return enrollmentRepository.findEnrollmentByStudent(studentId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Enrollment> findEnrollmentByCourse(Long courseId) {
		return enrollmentRepository.findEnrollmentByCourse(courseId);
	}

	@Override
	public Enrollment enrollStudent(Enrollment enrollment) {
		
		/*Optional<Enrollment> enrollmentOptional = enrollmentRepository.findEnrollmentByStudentAndCourse(enrollment.getStudent().getId(), enrollment.getCourse().getId());

		if (enrollmentOptional.isPresent()) {
			throw new ResourceAlreadyExistsException("701",
					"Enrollment for student id " + enrollment.getStudent().getId() + " with course id " + enrollment.getCourse().getId() + " already exists");
		}  */

		return enrollmentRepository.save(enrollment);	
	}

	@Override
	public void deleteEnrollmentByStudent(Long studentId) {
		/*boolean exists = enrollmentRepository.existsByStudent(studentId);
		if (!exists) {
			throw new ResourceNotFoundException("700", "Enrollment with student studentId " + id + " does not exists");
		}

		enrollmentRepository.deleteByStudentId(studentId); */

	}
	
	@Override
	public void deleteEnrollmentById(Long id) {
		boolean exists = enrollmentRepository.existsById(id);
		if (!exists) {
			throw new ResourceNotFoundException("700", "Enrollment with id " + id + " does not exists");
		}

		enrollmentRepository.deleteById(id);

	}

	@Override
	public Enrollment updateEnrollment(Long id, String status, LocalDate endDate, Float score) {
		Enrollment enrollment = enrollmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("700", "Enrollment with id " + id + " does not exists"));

		if (status != null && status.length() > 0 && !Objects.equals(enrollment.getStatus(), status)) {
			enrollment.setStatus(null);
		}

		if (endDate != null) {
			enrollment.setEndDate(endDate);
		}

		if (score != null) {
			enrollment.setScore(score);
		}
		return enrollmentRepository.save(enrollment);
	}

}
