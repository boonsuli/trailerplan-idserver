package com.trailerplan.idserver.config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.trailerplan.idserver.model.dto.SocialProviderDto;
import com.trailerplan.idserver.model.entity.RoleEntity;
import com.trailerplan.idserver.model.entity.UserEntity;
import com.trailerplan.idserver.repository.RoleRepository;
import com.trailerplan.idserver.repository.UserRepository;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(SetupDataLoader.class);

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}
		// Create initial roles
		RoleEntity userRole = createRoleIfNotFound(RoleEntity.ROLE_RUNNER);
		RoleEntity adminRole = createRoleIfNotFound(RoleEntity.ROLE_ADMIN);
		RoleEntity modRole = createRoleIfNotFound(RoleEntity.ROLE_MODERATOR);

		createAdminUserIfNotFound("admin@trailerplan.com", Set.of(userRole, adminRole, modRole));

		alreadySetup = true;
	}

	@Transactional
	public UserEntity createAdminUserIfNotFound(final String email, Set<RoleEntity> roles) {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null) {
			user = new UserEntity();
			user.setDisplayName("Admin");
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode("@dmInI5tr@t0r"));
			user.setRoles(roles);
			user.setProvider(SocialProviderDto.LOCAL.getProviderType());
			user.setEnabled(true);
			Date now = Calendar.getInstance().getTime();
			user.setCreatedDate(now);
			user.setModifiedDate(now);

			LOGGER.info("Create admin user");
			user = userRepository.save(user);

			updateRunnerPasswordAsEncodedInstagramAccountName(getAllEmailsFromRunner());
		}
		return user;
	}

	/**
	 * update the password only when admin user not exist
	 * @param emails
	 */
	private void updateRunnerPasswordAsEncodedInstagramAccountName(final List<String> emails) {
		if(emails!=null && !emails.isEmpty()) {
			Stream<String> stream = emails.stream();
			stream.forEach(email -> {
				UserEntity user = userRepository.findByEmail(email);
				String currentPassword = user.getPassword();
				user.setPassword(passwordEncoder.encode(currentPassword));
				Date now = Calendar.getInstance().getTime();
				user.setModifiedDate(now);
				userRepository.save(user);
			});
		}
	}

	private List<String> getAllEmailsFromRunner() {
		RoleEntity role = roleRepository.findByName(RoleEntity.ROLE_RUNNER);
		List<String> emails = new ArrayList();

		if(role!=null) {
			role.getUsers()
				.stream()
				.forEach( r -> emails.add(r.getEmail()));
		}
		return emails;
	}


	@Transactional
	public RoleEntity createRoleIfNotFound(final String name) {
		RoleEntity role = roleRepository.findByName(name);
		if (role == null) {
			role = roleRepository.save(new RoleEntity(name));
		}
		return role;
	}
}