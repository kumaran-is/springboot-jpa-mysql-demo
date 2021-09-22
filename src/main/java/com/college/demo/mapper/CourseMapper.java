package com.college.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.college.demo.dto.CourseDTO;
import com.college.demo.dto.FacultyDTO;
import com.college.demo.dto.StudentContactInfoDTO;
import com.college.demo.model.Course;
import com.college.demo.model.Faculty;
import com.college.demo.model.StudentContactInfo;


@Mapper(componentModel = "spring")
public interface  CourseMapper {
	
	// converting Entity to DTO
	CourseDTO toCourseDTO(Course course);
	
	// List of Entity to List of DTOs
    List<CourseDTO> toCourseDTOs(List<Course> courses);
    
    //DTO to Entity
    Course toCourse(CourseDTO courseDTO);
    
    // converting Entity to DTO
	FacultyDTO toFacultyDTO(Faculty faculty);
	
	// List of Entity to List of DTOs
    List<FacultyDTO> toFacultyDTOs(List<Faculty> faculties);
    
    //DTO to Entity
    Faculty toFaculty(FacultyDTO facultyDTO);
    
}
