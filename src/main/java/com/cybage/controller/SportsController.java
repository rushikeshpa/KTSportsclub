package com.cybage.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.exception.SportsNotFoundException;
import com.cybage.pojo.Logs;
import com.cybage.pojo.Sports;
import com.cybage.pojo.Users;
import com.cybage.service.LogsServiceImpl;
import com.cybage.service.SportsServieImpl;
import com.cybage.service.UserServieImpl;

@RestController

@RequestMapping("/sports")
public class SportsController {

	Logger logger = LogManager.getLogger("SportsController.class");

	@Autowired
	SportsServieImpl sportsServie;
	@Autowired
	UserServieImpl userServie;
	@Autowired
	LogsServiceImpl logsService;

	@GetMapping("/getAllSports")
	public List<Sports> getAllSports() {
		logger.debug("In get all sports Controller");
		List<Sports> sports = sportsServie.getAllSports();
		if (sports.size() == 0) {
			throw new SportsNotFoundException("Doesnt have any Sports");
		} else {
			return sports;
		}
	}

	@PostMapping("/addSport/{userId}")
	public ResponseEntity<String> addSport(@Valid @RequestBody Sports sports, @PathVariable int userId) {
		logger.debug("In add sports Controller");
		sportsServie.addSports(sports);

		// logs
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : New Sports Added : " + sports.getSportName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);	

		return new ResponseEntity<String>("Added sports", HttpStatus.CREATED);
	}

	@PostMapping("/sportsadd")
	public ResponseEntity<String> addSports(@Valid @RequestBody Sports sports) {
		logger.debug("In add sports Controller");
		sportsServie.addSports(sports);

//		// logs
//		Users user = userServie.getUserById(userId);
//		String logdesc = user.getEmail() + " : New Sports Added : " + sports.getSportName();
//		String logDate = LocalDateTime.now().toString();
//		Logs logs = new Logs(logdesc, logDate, user);
//		logsService.addLog(logs);

		return new ResponseEntity<String>("Added sports", HttpStatus.CREATED);
	}
	
	@PutMapping("/updateSports/{userId}")
	public ResponseEntity<String> updateSport(@RequestBody Sports sports, @PathVariable int userId) {
		logger.debug("In update sports Controller");
		sportsServie.updateSports(sports);

		// logs
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Sports Updated : " + sports.getSportName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("sports updated", HttpStatus.OK);
	}

	@DeleteMapping("/deleteSports/{id}/{userId}")
	public ResponseEntity<String> deleteSportById(@PathVariable int id, @PathVariable int userId) {
		logger.debug("In delete sports Controller");
		Sports sports = sportsServie.getSportsById(id);
		sportsServie.deleteSportsById(id);

		// logs
		Users user = userServie.getUserById(userId);
		System.out.println("NNNUSER" + user);
		String logdesc = user.getEmail() + " : Sports Deleted : " + sports.getSportName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	@GetMapping("/getSportsByName/{name}")
	public List<Sports> getSportsByName(@PathVariable String name) {
		logger.debug("In get sports by name Controller");
		List<Sports> sports = sportsServie.getSportsByName(name);
		if (sports.size() == 0) {
			throw new SportsNotFoundException("Doesent have sports by Name: " + name);
		} else {
			return sports;
		}
	}

	@GetMapping("/getOfferById/{id}")
	public Sports getSportsById(@PathVariable int id) {
		logger.debug("In get Sports By id");
		Sports sport = sportsServie.getSportsById(id);
		if (sport == null) {
			throw new SportsNotFoundException("Sport Not Found");
		} else {
			return sport;
		}
	}

	@GetMapping("/enableSport/{sportsId}/{userId}")
	public ResponseEntity<String> enableOffer(@PathVariable int sportsId, @PathVariable int userId) {
		logger.debug("in enabe sport of" + getClass().getName());
		String status = "true";
		sportsServie.statusOfSports(status, sportsId);

		// logs
		Sports sports = sportsServie.getSportsById(sportsId);
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Batch Enabled : " + sports.getSportName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("Sport enabled", HttpStatus.OK);
	}

	@GetMapping("/disableSport/{sportsId}/{userId}")
	public ResponseEntity<String> Offer(@PathVariable int sportsId, @PathVariable int userId) {
		logger.debug("in disable sport of" + getClass().getName());
		String status = "false";
		sportsServie.statusOfSports(status, sportsId);

		// logs
		Sports sports = sportsServie.getSportsById(sportsId);
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Batch Enabled : " + sports.getSportName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("sports disabled", HttpStatus.OK);
	}

}
