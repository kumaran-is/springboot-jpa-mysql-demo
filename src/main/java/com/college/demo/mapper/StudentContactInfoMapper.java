package com.college.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.college.demo.dto.StudentContactInfoDTO;
import com.college.demo.model.StudentContactInfo;

@Mapper(componentModel = "spring")
public interface StudentContactInfoMapper {

	// converting Entity to DTO
	StudentContactInfoDTO toStudentContactInfoDTO(StudentContactInfo studentContactInfo);
	
	// List of Entity to List of DTOs
    List<StudentContactInfoDTO> toStudentContactInfoDTOs(List<StudentContactInfo> studentContactInfos);
    
    //DTO to Entity
    StudentContactInfo toStudentContactInfo(StudentContactInfoDTO studentContactInfoDTO);
}
