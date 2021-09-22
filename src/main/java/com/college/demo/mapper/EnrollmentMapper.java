package com.college.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.college.demo.dto.CourseDTO;
import com.college.demo.dto.EnrollmentDTO;
import com.college.demo.dto.StudentContactInfoDTO;
import com.college.demo.dto.StudentDTO;
import com.college.demo.model.Course;
import com.college.demo.model.Enrollment;
import com.college.demo.model.Student;
import com.college.demo.model.StudentContactInfo;


@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
	

	// converting Entity to DTO
		EnrollmentDTO toEnrollmentDTO(Enrollment enrollment);
		
		// List of Entity to List of DTOs
	    List<EnrollmentDTO> toEnrollmentDTOs(List<Enrollment> enrollment);
	    
	    //DTO to Entity
	    Enrollment toEnrollment(EnrollmentDTO enrollmentDTO);
	
	// converting Entity to DTO
		CourseDTO toCourseDTO(Course course);
		
		// List of Entity to List of DTOs
	    List<CourseDTO> toCourseDTOs(List<Course> courses);
	    
	    //DTO to Entity
	    Course toCourse(CourseDTO courseDTO);
	    
	 // converting Entity to DTO
		StudentDTO toStudentDTO(Student student);
		
		// List of Entity to List of DTOs
	    List<StudentDTO> toStudentDTOs(List<Student> students);
	    
	    //DTO to Entity
	    Student toStudent(StudentDTO studentDTO);
	    
		// converting Entity to DTO
		StudentContactInfoDTO toStudentContactInfoDTO(StudentContactInfo studentContactInfo);
		
		// List of Entity to List of DTOs
	    List<StudentContactInfoDTO> toStudentContactInfoDTOs(List<StudentContactInfo> studentContactInfos);
	    
	    //DTO to Entity
	    StudentContactInfo toStudentContactInfo(StudentContactInfoDTO studentContactInfoDTO);

}
