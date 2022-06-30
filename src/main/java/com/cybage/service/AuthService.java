package com.cybage.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cybage.dao.UserRepository;
import com.cybage.dto.LoginDetails;
import com.cybage.pojo.NotificationEmailDTO;

@Service
public class AuthService {

	@Autowired
	private MailService mailService;

	@Autowired
	private UserRepository userRepository;

	public int signin(String username) {

		if (userRepository.existsByemail(username)) {
			final int generateOTP = generateOTP(username);
			return generateOTP;
		} else {
			throw new UsernameNotFoundException("No such username exists!!!");
		}
	}

	private int generateOTP(String username) {

		Random random = new Random();
		int sentOTP = random.nextInt(999999);

		String subject = "OTP for Login";
		String recipient = username;
		String body = "OTP for " + username + " is : " + sentOTP;

		mailService.sendMail(new NotificationEmailDTO(subject, recipient, body));

		return sentOTP;

	}

}
