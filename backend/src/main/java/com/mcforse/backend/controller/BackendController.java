package com.mcforse.backend.controller;

import com.mcforse.backend.model.User;
import com.mcforse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BackendController {

	@Autowired
    private UserRepository userRepository;

	@GetMapping("/usercontent")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String getUserContent(Authentication authentication) {
		User user = userRepository.findByUsername(authentication.getName()).get();
		return "Hello " + user.getUsername() + "!";
	}

	@GetMapping("/admincontent")
	@PreAuthorize("hasRole('ADMIN')")
	public String getAdminContent()  {
		return "Admin's content";
	}

}
