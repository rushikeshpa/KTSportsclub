package com.cybage.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.pojo.Logs;
import com.cybage.pojo.Membership;
import com.cybage.pojo.Offers;
import com.cybage.pojo.Users;
import com.cybage.service.LogsServiceImpl;
import com.cybage.service.MembershipServieImpl;
import com.cybage.service.UserServieImpl;

@RestController

@RequestMapping("membership")
public class MembershipController {

	Logger logger = LogManager.getLogger("UserController.class");

	@Autowired
	MembershipServieImpl membershipServie;
	@Autowired
	UserServieImpl userServie;
	@Autowired
	LogsServiceImpl logsService;

	@GetMapping("/getAllMemberships")
	public List<Membership> getAllMembership() {
		logger.debug("In get all membership Controller");
		return membershipServie.getAllMemberships();
	}

	@GetMapping("/getMembershipById/{id}")
	public Membership getMembershipById(@PathVariable int id) {
		logger.debug("In get Membership By id");
		return membershipServie.getMembershipById(id);
	}

	@GetMapping("/aprooveMembership/{userId}")
	public ResponseEntity<String> aprooveMembership(@PathVariable int userId) {
		logger.debug("Aproove membership of " + getClass().getName());
		String status = "APROOVED";
		membershipServie.changeStatus(status, userId);

		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " :  Aprooved membership";
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("Aprooved membership", HttpStatus.OK);

	}

	@GetMapping("/rejectMembership/{userId}")
	public ResponseEntity<String> rejectMembership(@PathVariable int userId) {
		logger.debug("Aproove membership of " + getClass().getName());
		String status = "REJECTED";
		membershipServie.changeStatus(status, userId);
		
		//logs
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " :  Rejected membership";
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("Rejected membership", HttpStatus.OK);

	}

}
