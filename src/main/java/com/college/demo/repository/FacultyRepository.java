package com.college.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.college.demo.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {


}
