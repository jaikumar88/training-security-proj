package com.ntss.security.app.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntss.security.app.payload.RegistrationRequest;
import com.ntss.security.app.service.UserService;
import com.ntss.security.app.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(final AuthenticationManager authenticationManager, final JwtUtil jwtUtil, final UserService userService) {
    	this.authenticationManager = authenticationManager;
    	this.jwtUtil = jwtUtil;
    	this.userService = userService;
    	
	}
    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest registrationRequest) {
        userService.registerUser(registrationRequest.getUserName(), registrationRequest.getPassword(), registrationRequest.getRoles());
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtUtil.generateToken(username);
        } catch (AuthenticationException e) {
        	e.printStackTrace();
            return "Invalid credentials";
        }
    }
    
    
}
