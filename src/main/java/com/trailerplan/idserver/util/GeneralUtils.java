package com.trailerplan.idserver.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.trailerplan.idserver.model.dto.LocalUserDto;
import com.trailerplan.idserver.model.dto.SocialProviderDto;
import com.trailerplan.idserver.model.dto.UserInfoDto;
import com.trailerplan.idserver.model.entity.RoleEntity;
import com.trailerplan.idserver.model.entity.UserEntity;

public class GeneralUtils {

	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<RoleEntity> roles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	public static SocialProviderDto toSocialProvider(String providerId) {
		for (SocialProviderDto socialProvider : SocialProviderDto.values()) {
			if (socialProvider.getProviderType().equals(providerId)) {
				return socialProvider;
			}
		}
		return SocialProviderDto.LOCAL;
	}

	public static UserInfoDto buildUserInfo(LocalUserDto localUser) {
		List<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
		UserEntity user = localUser.getUser();
		return new UserInfoDto(user.getId().toString(), user.getDisplayName(), user.getEmail(), roles);
	}
}
