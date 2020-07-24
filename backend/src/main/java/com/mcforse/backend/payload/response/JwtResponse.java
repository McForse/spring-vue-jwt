package com.mcforse.backend.payload.response;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class JwtResponse {

	String type = "Bearer";

	String accessToken;

	String username;

	List<GrantedAuthority> roles;

	public JwtResponse(String accessToken, String username, List<GrantedAuthority> roles) {
		this.accessToken = accessToken;
		this.username = username;
		this.roles = roles;
	}

}
