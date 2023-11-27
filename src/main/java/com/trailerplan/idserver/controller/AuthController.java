package com.trailerplan.idserver.controller;

import javax.validation.Valid;

import com.trailerplan.idserver.model.dto.ApiResponseDto;
import com.trailerplan.idserver.model.dto.JwtAuthenticationResponseDto;
import com.trailerplan.idserver.model.dto.LocalUserDto;
import com.trailerplan.idserver.model.dto.LoginRequestDto;
import com.trailerplan.idserver.model.dto.SignUpRequestDto;
import com.trailerplan.idserver.exception.UserAlreadyExistAuthenticationException;
import com.trailerplan.idserver.security.jwt.TokenProvider;
import com.trailerplan.idserver.service.UserService;
import com.trailerplan.idserver.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	TokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {
		UsernamePasswordAuthenticationToken usernamePswd = new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail(), loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(usernamePswd);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);
		LocalUserDto localUser = (LocalUserDto) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtAuthenticationResponseDto(jwt, GeneralUtils.buildUserInfo(localUser)));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestDto signUpRequest) {
		try {
			userService.registerNewUser(signUpRequest);
		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponseDto(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponseDto(true, "User registered successfully"));
	}
}