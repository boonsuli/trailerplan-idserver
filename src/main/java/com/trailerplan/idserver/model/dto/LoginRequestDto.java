package com.trailerplan.idserver.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequestDto {
	@NotBlank
	private String email;

	@NotBlank
	private String password;
}