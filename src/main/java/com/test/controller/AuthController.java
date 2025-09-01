package com.test.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public String login(@RequestBody UserEntity request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid Credentials");
        }
        return jwtUtil.generateToken(request.getEmail());
    }
    
    @PostMapping("/register")
    public ResponseEntity<HttpResponseDTO<String>> registerUser(@RequestBody UserEntity user){
    	
    	userService.saveUser(user);
    	return ResponseEntity.ok(new HttpResponseDTO<>(HttpStatus.ACCEPTED,"Saved","Saved Successful"));
    }
}



record AuthRequest(String username, String password) {}

