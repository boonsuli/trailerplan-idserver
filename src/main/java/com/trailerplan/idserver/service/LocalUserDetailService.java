package com.trailerplan.idserver.service;

import com.trailerplan.idserver.model.dto.LocalUserDto;
import com.trailerplan.idserver.exception.ResourceNotFoundException;
import com.trailerplan.idserver.model.entity.UserEntity;
import com.trailerplan.idserver.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("localUserDetailService")
public class LocalUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public LocalUserDto loadUserByUsername(final String email) throws UsernameNotFoundException {
		UserEntity user = userService.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		return createLocalUser(user);
	}

	@Transactional
	public LocalUserDto loadUserById(Long id) {
		UserEntity user = userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return createLocalUser(user);
	}

	/**
	 * @param user
	 * @return
	 */
	private LocalUserDto createLocalUser(UserEntity user) {
		return new LocalUserDto(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
	}
}
