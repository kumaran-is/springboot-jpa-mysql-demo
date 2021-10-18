package com.college.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.college.demo.model.Faculty;


@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

	@Transactional(readOnly = true)
	// @Query("SELECT f FROM Faculty f where f.email = ?1")
	Optional<Faculty> findFacultyByEmail(String email);
}
