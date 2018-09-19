package com.dawncheng.springboot.blog.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;

public class ConstraintViolationExceptionHandler {

	private ConstraintViolationExceptionHandler() {
	}

	public static String getMessage(ConstraintViolationException e) {
		List<String> messages = new ArrayList<>();
		for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
			messages.add(constraintViolation.getMessage());
		}
		return StringUtils.join(messages.toArray(), ":");
	}
}
