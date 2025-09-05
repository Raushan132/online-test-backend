package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.config.JwtUtil;
import com.test.dto.HttpResponseDTO;
import com.test.model.UserEntity;
import com.test.service.IUserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	@Autowired
	private IUserService userService;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@GetMapping("/verify")
	public ResponseEntity<String> verifyAccount(@RequestParam("token") String token, @RequestParam("id") Integer id) {

		boolean status = userService.userVerification(token, id);

		if (status) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account verified successfully!");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token or user ID");
		}
	}

	@PostMapping("/login")
	public String login(@RequestBody UserEntity request) {
		try {

			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (AuthenticationException e) {
			throw new RuntimeException("Invalid Credentials");
		}
		return jwtUtil.generateToken(request.getEmail());
	}

	@PostMapping("/register")
	public ResponseEntity<HttpResponseDTO<String>> registerUser(@RequestBody UserEntity user) {

		userService.saveUser(user);
		return ResponseEntity.ok(new HttpResponseDTO<>(HttpStatus.ACCEPTED, "Saved", "Saved Successful"));
	}
}

record AuthRequest(String username, String password) {
}
