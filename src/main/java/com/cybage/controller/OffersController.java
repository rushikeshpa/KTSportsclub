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

import com.cybage.exception.OfferAlreadyExistsException;
import com.cybage.exception.OfferNotFoundException;
import com.cybage.pojo.Logs;
import com.cybage.pojo.Offers;
import com.cybage.pojo.Users;
import com.cybage.service.LogsServiceImpl;
import com.cybage.service.OffersServieImpl;
import com.cybage.service.UserServieImpl;

@RestController

@RequestMapping("/offers")
public class OffersController {

	Logger logger = LogManager.getLogger("OffersController.class");

	@Autowired
	OffersServieImpl offersServie;
	@Autowired
	UserServieImpl userServie;
	@Autowired
	LogsServiceImpl logsService;

	@GetMapping("/getAllOffers")
	public List<Offers> getAllOffers() {
		logger.debug("In get all offers Controller");
		List<Offers> offers = offersServie.getAllOffers();
		if (offers.size() == 0) {
			throw new OfferNotFoundException("Doesnt have any offer");
		} else
			return offers;

	}

	@PostMapping("/addOffer/{userId}")
	public ResponseEntity<String> addOffer(@Valid @RequestBody Offers offer, @PathVariable int userId) {
		logger.debug("In add offers Controller");
		offersServie.addOffer(offer);

		// logs
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : New Offer Added : " + offer.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("Added!", HttpStatus.CREATED);

	}

	@PutMapping("/updateOffer/{userId}")
	public ResponseEntity<String> updateOffer(@Valid @RequestBody Offers offer, @PathVariable int userId) {
		logger.debug("In update offers Controller");
		offersServie.updateOffer(offer);

		// logs
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Offer updated : " + offer.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("updated offer", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}/{userId}")
	public ResponseEntity<String> deleteOfferById(@PathVariable int id, @PathVariable int userId) {
		logger.debug("In delete by id offers controller");
		Offers offer = offersServie.getOfferById(id);
		offersServie.deleteOfferById(id);

		// logs
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Offer Deleted : " + offer.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("Deleted offer", HttpStatus.OK);
	}

	@GetMapping("/getOfferByName")
	public List<Offers> getOfferByName(String name) {
		logger.debug("In get by name offers controller");
		List<Offers> list = offersServie.getOfferByName(name);
		if (list.size() == 0) {
			throw new OfferNotFoundException("Doesent have offer with name: " + name);
		} else {
			return list;
		}
	}

	@GetMapping("/getOfferById/{id}")
	public Offers getOfferById(@PathVariable int id) {
		logger.debug("In get Offer By id");
		Offers offer = offersServie.getOfferById(id);
		if (offer == null) {
			throw new OfferNotFoundException("Offer Not Found");
		} else {
			return offer;
		}

	}

	@PutMapping("/enableOffer/{offerId}/{userId}")
	public ResponseEntity<String> enableOffer(@PathVariable int offerId, @PathVariable int userId) {
		logger.debug("In enable offer of: " + getClass().getName());
		String status = "true";
		offersServie.statusOfOffer(status, offerId);

		// logs
		Offers offer = offersServie.getOfferById(offerId);
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Offer Enabled : " + offer.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("offer enabled", HttpStatus.OK);

	}

	@PutMapping("/disableOffer/{offerId}/{userId}")
	public ResponseEntity<String> disableOffer(@PathVariable int offerId, @PathVariable int userId) {
		logger.debug("In disable offer of: " + getClass().getName());
		String status = "false";
		offersServie.statusOfOffer(status, offerId);

		// logs
		Offers offer = offersServie.getOfferById(offerId);
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Offer Enabled : " + offer.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("offer disabled", HttpStatus.OK);

	}

	@GetMapping("/likeOffer/{offerId}/{userId}")
	public int likeOffer(@PathVariable int offerId, @PathVariable int userId) {
		logger.debug("In like offer of: " + getClass().getName());

		// logs
		Offers offer = offersServie.getOfferById(offerId);
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Offer liked : " + offer.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return offersServie.likeOffer(offerId);
	}

	@GetMapping("/disLikeOffer/{offerId}/{userId}")
	public int disLikeOffer(@PathVariable int offerId, @PathVariable int userId) {
		logger.debug("In dislike offer of: " + getClass().getName());

		// logs
		Offers offer = offersServie.getOfferById(offerId);
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Offer disliked : " + offer.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return offersServie.disLikeOffer(offerId);
	}

}
