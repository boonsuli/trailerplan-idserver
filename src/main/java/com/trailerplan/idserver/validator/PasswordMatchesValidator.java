package com.trailerplan.idserver.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.trailerplan.idserver.model.dto.SignUpRequestDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignUpRequestDto> {

	@Override
	public boolean isValid(final SignUpRequestDto user, final ConstraintValidatorContext context) {
		return user.getPassword().equals(user.getMatchingPassword());
	}
}
