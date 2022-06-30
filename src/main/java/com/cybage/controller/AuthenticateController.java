package com.cybage.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.config.JwtUtils;
import com.cybage.pojo.JwtRequest;
import com.cybage.pojo.JwtResponse;
import com.cybage.pojo.Users;
import com.cybage.service.UserDetailsServiceImpl;
import com.cybage.service.UserServieImpl;
 
@RestController

public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticateManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	

	@Autowired
	UserServieImpl userServie;

	@Autowired
	private JwtUtils jwtUtils;

	// generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generatedToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			System.out.println("Inside gc");
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User Not found");
		}

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		System.out.println(userDetails);
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			
			System.out.println(new UsernamePasswordAuthenticationToken(username, password));
	authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			 
		} catch (DisabledException e) {
			throw new Exception("USER DISABLED" + e.getMessage());
		} catch (BadCredentialsException e) {
		  userServie.updateCount(username);
		   throw new Exception("Invalid Credentails" + e.getMessage());
		}
	}

	// return the detail of current user
	@GetMapping("/current-user")
	public Users getCurrentUser(Principal principal) {
		
		return (Users) this.userDetailsService.loadUserByUsername(principal.getName());
	}
}
