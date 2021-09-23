package com.college.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.college.demo.dto.FacultyDTO;
import com.college.demo.model.Faculty;

@Mapper(componentModel = "spring")
public interface FacultyMapper {
	// converting Entity to DTO
	FacultyDTO toFacultyDTO(Faculty faculty);
	
	// List of Entity to List of DTOs
    List<FacultyDTO> toFacultyDTOs(List<Faculty> faculties);
    
    //DTO to Entity
    Faculty toFaculty(FacultyDTO facultyDTO);
}
