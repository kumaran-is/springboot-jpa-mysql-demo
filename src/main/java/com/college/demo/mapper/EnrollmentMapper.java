package com.college.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.college.demo.dto.EnrollmentDTO;
import com.college.demo.model.Enrollment;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
	// converting Entity to DTO
	EnrollmentDTO toEnrollmentDTO(Enrollment enrollment);
	
	// List of Entity to List of DTOs
    List<EnrollmentDTO> toEnrollmentDTOs(List<Enrollment> enrollment);
    
    //DTO to Entity
    Enrollment toEnrollment(EnrollmentDTO enrollmentDTO);
}
