package com.college.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.college.demo.constants.Status;
import com.college.demo.exception.ResourceAlreadyExistsException;
import com.college.demo.exception.ResourceNotFoundException;
import com.college.demo.model.Enrollment;
import com.college.demo.repository.EnrollmentRepository;

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
	@Transactional(readOnly = true)
	public List<Enrollment> findEnrollmentByStatus(String status) {
		return enrollmentRepository.findByStatus(Status.valueOf(status));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Enrollment findEnrollmentByStudentandCourse(Long studentId, Long courseId) {
		 return enrollmentRepository.findEnrollmentByStudentAndCourse(studentId, courseId).orElseThrow(
					() -> new ResourceNotFoundException("700", "Enrollment with student id " + studentId + " and  course id " + courseId + " does not exists"));
	}

	@Override
	public Enrollment enrollStudent(Enrollment enrollment) {
		
		Optional<Enrollment> enrollmentOptional = enrollmentRepository.findEnrollmentByStudentAndCourse(enrollment.getStudentId(), enrollment.getCourseId());
		if (enrollmentOptional.isPresent()) {
			throw new ResourceAlreadyExistsException("701",
					"Enrollment for student id " + enrollment.getStudentId() + " with course id " + enrollment.getCourseId() + " already exists");
		}

		return enrollmentRepository.save(enrollment);	
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
	public void deleteEnrollmentByStudent(Long studentId) {
		boolean exists = enrollmentRepository.existsByStudent(studentId);
		if (!exists) {
			throw new ResourceNotFoundException("700", "Enrollment with student id " + studentId + " does not exists");
		}

		enrollmentRepository.deleteByStudent(studentId); 

	}
	
	@Override
	public void deleteEnrollmentByCourse(Long courseId) {
		boolean exists = enrollmentRepository.existsByCourse(courseId);
		if (!exists) {
			throw new ResourceNotFoundException("700", "Enrollment with course id " + courseId + " does not exists");
		}

		enrollmentRepository.deleteByCourse(courseId); 

	}
	
	@Override
	public void deleteEnrollmentByStatus(String status) {
		boolean exists = enrollmentRepository.existsByStatus(Status.valueOf(status));
		if (!exists) {
			throw new ResourceNotFoundException("700", "Enrollment with status " + status + " does not exists");
		}

		enrollmentRepository.deleteByStatus(Status.valueOf(status)); 

	}
	
	@Override
	public void deleteEnrollmentByStudentAndCourse(Long studentId, Long courseId) {
		boolean exists = enrollmentRepository.existsByStudentAndCourse(studentId, courseId);
		if (!exists) {
			throw new ResourceNotFoundException("700", "Enrollment with student id " + studentId + " and course id "+courseId+" does not exists");
		}

	   enrollmentRepository.deleteByStudentandCourse(studentId, courseId); 

	}
	

	@Override
	public Enrollment updateEnrollment(Long id, String status, LocalDate endDate, Float score) {
		Enrollment enrollment = enrollmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("700", "Enrollment with id " + id + " does not exists"));

		if (status != null) {
			enrollment.setStatus(Status.valueOf(status));
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
