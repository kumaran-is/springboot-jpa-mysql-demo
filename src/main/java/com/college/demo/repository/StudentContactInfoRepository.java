package com.college.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.college.demo.model.StudentContactInfo;

@Repository
public interface StudentContactInfoRepository extends JpaRepository<StudentContactInfo, Long> {


}