package com.college.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.college.demo.model.Student;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Transactional(readOnly = true)
	// @Query("SELECT s FROM Student s where s.email = ?1")
	Optional<Student> findStudentByEmail(String email);

}
