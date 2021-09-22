package com.college.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.college.demo.dto.CourseDTO;
import com.college.demo.dto.FacultyDTO;
import com.college.demo.model.Course;
import com.college.demo.model.Faculty;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

	// converting Entity to DTO
		FacultyDTO toFacultyDTO(Faculty faculty);
		
		// List of Entity to List of DTOs
	    List<FacultyDTO> toFacultyDTOs(List<Faculty> faculties);
	    
	    //DTO to Entity
	    Faculty toFaculty(FacultyDTO facultyDTO);
	    
	 // converting Entity to DTO
		CourseDTO toCourseDTO(Course course);
		
		// List of Entity to List of DTOs
	    List<CourseDTO> toCourseDTOs(List<Course> courses);
	    
	    //DTO to Entity
	    Course toCourse(CourseDTO courseDTO);
	    
}
