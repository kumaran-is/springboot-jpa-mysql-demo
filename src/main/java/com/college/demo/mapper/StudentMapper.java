package com.college.demo.mapper;

import org.mapstruct.Mapper;

import com.college.demo.dto.StudentContactInfoDTO;
import com.college.demo.dto.StudentDTO;
import com.college.demo.model.Student;
import com.college.demo.model.StudentContactInfo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	
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
