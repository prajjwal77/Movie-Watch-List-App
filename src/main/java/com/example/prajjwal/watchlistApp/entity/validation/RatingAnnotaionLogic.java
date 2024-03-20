package com.example.prajjwal.watchlistApp.entity.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingAnnotaionLogic implements ConstraintValidator<Rating, Float> {

	@Override
	public boolean isValid(Float value, ConstraintValidatorContext context) {
		
		return value > 5.00 && value<=10.00;
	}
}
