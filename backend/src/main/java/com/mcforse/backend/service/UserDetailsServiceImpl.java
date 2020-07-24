package com.mcforse.backend.service;

import com.mcforse.backend.exception.UserAlreadyExistException;
import com.mcforse.backend.payload.request.SignUpRequest;
import com.mcforse.backend.model.Role;
import com.mcforse.backend.model.User;
import com.mcforse.backend.repository.RoleRepository;
import com.mcforse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		if (!user.isPresent()) throw new UsernameNotFoundException("User not found!");

		return user.get();
	}

	public void addUser(SignUpRequest newUser) throws UserAlreadyExistException {
		if (usernameExists(newUser.getUsername())) {
			throw new UserAlreadyExistException("User already exists!");
		}

		User user = new User(
				newUser.getUsername(),
				encoder.encode(newUser.getPassword()),
				new HashSet<Role>() {{
					add(roleRepository.findByName("ROLE_USER"));
				}}
		);

		userRepository.save(user);
	}

	private Boolean usernameExists(String username) {
		return userRepository.findByUsername(username).isPresent();
	}

}
