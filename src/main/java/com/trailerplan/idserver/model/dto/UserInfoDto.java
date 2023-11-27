package com.trailerplan.idserver.model.dto;

import java.util.List;

import lombok.Value;

@Value
public class UserInfoDto {
	private String id, displayName, email;
	private List<String> roles;
}