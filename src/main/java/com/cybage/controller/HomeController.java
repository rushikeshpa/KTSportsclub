package com.cybage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dto.LoginDetails;
import com.cybage.service.AuthService;

@RestController
@RequestMapping("/api/v1")

public class HomeController {

	@Autowired
	private AuthService authService;

	// landing page
	@GetMapping("/")
	public String homePage() {
		return "home";
	}

	// process login form
	// get dto object for getting login credentials
	@GetMapping("/generate-otp/{email}")
	public ResponseEntity<Integer> loginPage(@PathVariable("email") String loginDetails) {

		int sentOtp = authService.signin(loginDetails);
		return new ResponseEntity<Integer>(sentOtp,HttpStatus.OK);
	}



}
