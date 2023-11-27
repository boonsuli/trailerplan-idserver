package com.trailerplan.idserver.model.dto;

import lombok.Value;

@Value
public class JwtAuthenticationResponseDto {
	private String accessToken;
	private UserInfoDto user;
}