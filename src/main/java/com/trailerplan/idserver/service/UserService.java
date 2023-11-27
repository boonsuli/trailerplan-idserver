package com.trailerplan.idserver.service;

import java.util.Map;
import java.util.Optional;

import com.trailerplan.idserver.model.dto.LocalUserDto;
import com.trailerplan.idserver.model.dto.SignUpRequestDto;
import com.trailerplan.idserver.exception.UserAlreadyExistAuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.trailerplan.idserver.model.entity.UserEntity;

public interface UserService {

	public UserEntity registerNewUser(SignUpRequestDto signUpRequest) throws UserAlreadyExistAuthenticationException;

	UserEntity findUserByEmail(String email);

	Optional<UserEntity> findUserById(Long id);

	LocalUserDto processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
}
