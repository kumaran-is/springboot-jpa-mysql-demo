package com.college.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {
	private String object;
	private String field;
    private Object rejectedValue;
    private String message;
}
