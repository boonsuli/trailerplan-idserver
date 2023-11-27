package com.trailerplan.idserver.model.dto;

import java.util.Collection;
import java.util.Map;

import com.trailerplan.idserver.model.entity.UserEntity;
import com.trailerplan.idserver.util.GeneralUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class LocalUserDto extends User implements OAuth2User, OidcUser {

	private static final long serialVersionUID = -2845160792248762779L;
	private final OidcIdToken idToken;
	private final OidcUserInfo userInfo;
	private Map<String, Object> attributes;
	private UserEntity userEntity;

	public LocalUserDto(final String userID, final String password, final boolean enabled, final boolean accountNonExpired,
						final boolean credentialsNonExpired, final boolean accountNonLocked,
						final Collection<? extends GrantedAuthority> authorities, final UserEntity userEntity) {
		this(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities,
				userEntity, null, null);
	}

	public LocalUserDto(final String userID, final String password, final boolean enabled, final boolean accountNonExpired,
						final boolean credentialsNonExpired, final boolean accountNonLocked,
						final Collection<? extends GrantedAuthority> authorities, final UserEntity userEntity,
						OidcIdToken idToken, OidcUserInfo userInfo) {
		super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userEntity = userEntity;
		this.idToken = idToken;
		this.userInfo = userInfo;
	}

	public static LocalUserDto create(UserEntity userEntity, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
		LocalUserDto localUser = new LocalUserDto(userEntity.getEmail(), userEntity.getPassword(),
				userEntity.isEnabled(), true, true, true,
				GeneralUtils.buildSimpleGrantedAuthorities(userEntity.getRoles()), userEntity, idToken, userInfo);
		localUser.setAttributes(attributes);
		return localUser;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return this.userEntity.getDisplayName();
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public Map<String, Object> getClaims() {
		return this.attributes;
	}

	@Override
	public OidcUserInfo getUserInfo() {
		return this.userInfo;
	}

	@Override
	public OidcIdToken getIdToken() {
		return this.idToken;
	}

	public UserEntity getUser() {
		return userEntity;
	}
}
