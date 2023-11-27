package com.trailerplan.idserver.model.dto;

import lombok.Value;

@Value
public class ApiResponseDto {
	private Boolean success;
	private String message;
}