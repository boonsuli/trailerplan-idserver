package com.trailerplan.idserver.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SocialProviderDto {

	FACEBOOK("facebook"),
	TWITTER("twitter"),
	LINKEDIN("linkedin"),
	GOOGLE("google"),
	GITHUB("github"),
	TRAILERPLAN("trailerplan"),
	LOCAL("local");

	private String providerType;

	@JsonValue
	public String getProviderType() {
		return providerType;
	}

	SocialProviderDto(final String providerType) {
		this.providerType = providerType;
	}

}
