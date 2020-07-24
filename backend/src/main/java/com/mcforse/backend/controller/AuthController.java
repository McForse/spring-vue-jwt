package com.mcforse.backend.controller;

import com.mcforse.backend.jwt.JwtProvider;
import com.mcforse.backend.model.*;
import com.mcforse.backend.payload.request.LoginRequest;
import com.mcforse.backend.payload.request.SignUpRequest;
import com.mcforse.backend.payload.response.JwtResponse;
import com.mcforse.backend.payload.response.ResponseMessage;
import com.mcforse.backend.service.UserDetailsServiceImpl;
import com.mcforse.backend.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Value("${authCookieName}")
	private String authCookieName;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		User user = userDetailsService.loadUserByUsername(loginRequest.getUsername());

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(user.getUsername());
		CookieUtils.addCookie(response, authCookieName, jwt, jwtProvider.getJwtExpiration());
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, user.getUsername(), authorities));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
		userDetailsService.addUser(signUpRequest);
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response) {
		CookieUtils.deleteCookie(response, authCookieName);
		return ResponseEntity.ok(new ResponseMessage("Successfully logout"));
	}

}
