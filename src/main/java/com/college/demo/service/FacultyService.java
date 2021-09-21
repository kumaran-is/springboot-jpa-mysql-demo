package com.college.demo.service;

import java.util.List;

import com.college.demo.model.Faculty;

public interface FacultyService {

	public List<Faculty> getAllFaculties();

	public Faculty findFacultyByEmail(String email);

	public Faculty addNewFaculty(Faculty faculty);

	public void deleteFaculty(Long id);

	public Faculty updateFaculty(Long id, String firstName, String lastName, String email);
}
