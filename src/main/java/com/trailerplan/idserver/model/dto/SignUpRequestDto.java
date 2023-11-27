package com.trailerplan.idserver.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.trailerplan.idserver.validator.PasswordMatches;
import lombok.Data;

@Data
@PasswordMatches
public class SignUpRequestDto {

	private Long userID;

	private String providerUserId;

	@NotEmpty
	private String displayName;

	@NotEmpty
	private String email;

	private SocialProviderDto socialProvider;

	@Size(min = 8, message = "{trailerplan.idserver.dto.user.password.length}")
	private String password;

	@NotEmpty
	private String matchingPassword;

	public SignUpRequestDto(String providerUserId, String displayName, String email, String password, SocialProviderDto socialProvider) {
		this.providerUserId = providerUserId;
		this.displayName = displayName;
		this.email = email;
		this.password = password;
		this.socialProvider = socialProvider;
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public static class Builder {
		private String providerUserID;
		private String displayName;
		private String email;
		private String password;
		private SocialProviderDto socialProvider;

		public Builder addProviderUserID(final String userID) {
			this.providerUserID = userID;
			return this;
		}

		public Builder addDisplayName(final String displayName) {
			this.displayName = displayName;
			return this;
		}

		public Builder addEmail(final String email) {
			this.email = email;
			return this;
		}

		public Builder addPassword(final String password) {
			this.password = password;
			return this;
		}

		public Builder addSocialProvider(final SocialProviderDto socialProvider) {
			this.socialProvider = socialProvider;
			return this;
		}

		public SignUpRequestDto build() {
			return new SignUpRequestDto(providerUserID, displayName, email, password, socialProvider);
		}
	}
}
