package com.college.demo.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.demo.dto.FacultyDTO;
import com.college.demo.exception.InvalidInputException;
import com.college.demo.mapper.FacultyMapper;
import com.college.demo.model.Faculty;
import com.college.demo.service.FacultyService;
import com.college.demo.util.CommonUtils;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/faculties")
public class FacultyController {

	@Autowired
	private FacultyService facultyService;
	@Autowired
	private FacultyMapper facultyMapper;

	@GetMapping("/{email}")
	@ApiOperation("Returns a faculty by email")
	public ResponseEntity<FacultyDTO> findFacultyByEmail(@Valid @PathVariable("email") String email) {
		if (CommonUtils.isBlankString(email)) {
			throw new InvalidInputException("703", "Email is null or blank");
		}
		// return 200, with JSON body
		return ResponseEntity.ok().body(facultyMapper.toFacultyDTO(facultyService.findFacultyByEmail(email)));
	}

	@GetMapping
	@ApiOperation("Returns all the faculties")
	public ResponseEntity<List<FacultyDTO>> findAllFaculties() {
		// return 200, with JSON body
		return ResponseEntity.ok().body(facultyMapper.toFacultyDTOs(facultyService.getAllFaculties()));
	}

	@PostMapping
	@ApiOperation("Add a new faculty")
	public ResponseEntity<FacultyDTO> addNewFaculty(@Valid @RequestBody FacultyDTO facultyDTO) throws URISyntaxException {

		Faculty savedFaculty = facultyService.addNewFaculty(facultyMapper.toFaculty(facultyDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(facultyMapper.toFacultyDTO(savedFaculty));
		// return ResponseEntity.created(new URI("/api/v1/faculty/" +
		// savedFaculty.getId())).body(facultyMapper.toFacultyDTO(savedFaculty));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete a faculty")
	public ResponseEntity<Void> deleteFacultyById(@PathVariable("id") Long id) {
		if (null == id || id.equals(0L)) {
			throw new InvalidInputException("702", "Id is not valid");
		}
		facultyService.deleteFaculty(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@ApiOperation("Update a faculty")
	public ResponseEntity<FacultyDTO> modifyFaculty(@PathVariable("id") Long id,
			@RequestParam(name = "firstName", required = false) String firstName,
			@RequestParam(name = "lastName", required = false) String lastName,
			@RequestParam(name = "email", required = false) String email) {
		if (null == id || id.equals(0L)) {
			throw new InvalidInputException("702", "Id is not valid");
		}
		return ResponseEntity.ok()
				.body(facultyMapper.toFacultyDTO(facultyService.updateFaculty(id, firstName, lastName, email)));

	}
}
