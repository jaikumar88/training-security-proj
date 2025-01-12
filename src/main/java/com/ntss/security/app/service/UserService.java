/**
 * 
 */
package com.ntss.security.app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ntss.security.app.model.Role;
import com.ntss.security.app.model.User;
import com.ntss.security.app.repository.RoleRepository;
import com.ntss.security.app.repository.UserRepository;

/**
 * @author Owner
 *
 */
@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	public UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder,
			final RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public void registerUser(String username, String password, List<String> roles) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		Set<Role> rolesList = new HashSet<>();

		roles.forEach(roleName -> {
			Role role = roleRepository.findByName(roleName);
			rolesList.add(role);
		});

		user.setRoles(rolesList);
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getAuthorities() // This includes the roles/authorities
		);
	}

}
