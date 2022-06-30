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

import com.cybage.dao.BatchesRepository;
import com.cybage.exception.BatchNotFoundException;
import com.cybage.exception.OfferNotFoundException;
import com.cybage.pojo.Batches;
import com.cybage.pojo.Logs;
import com.cybage.pojo.Membership;
import com.cybage.pojo.Users;
import com.cybage.service.BatchesServieImpl;
import com.cybage.service.LogsServiceImpl;
import com.cybage.service.MembershipServieImpl;
import com.cybage.service.UserServieImpl;

@RestController

@RequestMapping("batches")
public class BatchesController {

	Logger logger = LogManager.getLogger("BatchesController.class");

	@Autowired
	BatchesServieImpl batchesServie;
	@Autowired
	UserServieImpl userServie;
	@Autowired
	LogsServiceImpl logsService;
	@Autowired
	MembershipServieImpl membershipServie;



	@GetMapping("/getAllBatches")
	public List<Batches> getAllBatches() {
		logger.debug("In get all batches Controller");
		List<Batches> batches = batchesServie.getAllBatches();
		if (batches.size() == 0) {
			throw new BatchNotFoundException("Doesnt have any batch");
		} else {
			return batches;
		}

	}

	@PostMapping("/addBatch/{userId}")
	public ResponseEntity<String> addBatch(@Valid @RequestBody Batches batch, @PathVariable int userId) {
		logger.debug("In add Batches Controller");
		batchesServie.addBatch(batch);

		// logs
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : New Batch Added : " + batch.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);

		return new ResponseEntity<String>("Added batch", HttpStatus.CREATED);
	}

	@PutMapping("/updateBatch/{userId}")
	public ResponseEntity<String> updateBatch(@RequestBody Batches batch, @PathVariable int userId) {
		logger.debug("In update Batch controller");
		batchesServie.updateBatch(batch);

		// logs
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Batch Updated : " + batch.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("updated batch", HttpStatus.OK);
	}

	@DeleteMapping("/deleteBatch/{id}/{userId}")
	public ResponseEntity<String> deleteBatch(@PathVariable int id, @PathVariable int userId) {
		logger.debug("In delete Batch Dao");
		Batches batch = batchesServie.getBatchesById(id);
		batchesServie.deleteBatchesById(id);

		// logs
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Batch Deleted : " + batch.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("batch deleted", HttpStatus.OK);
	}

	@GetMapping("/getBatchByName/{name}")
	public List<Batches> getBatchByName(@PathVariable String name) {
		logger.debug("In get Batches By name");
		List<Batches> batches = batchesServie.getBatchByName(name);
		if (batches.size() == 0) {
			throw new BatchNotFoundException("Batch Not Found");
		} else {
			return batches;
		}
	}

	@GetMapping("/getBatchById/{id}")
	public Batches getBatchById(@PathVariable int id) {
		logger.debug("In get Batch By id");
		Batches batch = batchesServie.getBatchesById(id);
		if (batch == null) {
			throw new OfferNotFoundException("Offer Not Found");
		} else {
			return batch;
		}
	}

	@PutMapping("/enableBatch/{batchId}/{userId}")
	public ResponseEntity<String> enableBatch(@PathVariable int batchId, @PathVariable int userId) {
		logger.debug("In enable batch of: " + getClass().getName());
		String status = "true";
		batchesServie.statusOfBatch(status, batchId);

		// logs
		Batches batch = batchesServie.getBatchesById(batchId);
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Batch Enabled : " + batch.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("batch enabled", HttpStatus.OK);
	}

	@PutMapping("/disableBatch/{batchId}/{userId}")
	public ResponseEntity<String> disableBatch(@PathVariable int batchId, @PathVariable int userId) {
		logger.debug("In disable batch of: " + getClass().getName());
		String status = "false";
		batchesServie.statusOfBatch(status, batchId);

		// logs
		Batches batch = batchesServie.getBatchesById(batchId);
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Batch Disabled : " + batch.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		logsService.addLog(logs);
		return new ResponseEntity<String>("batch disable", HttpStatus.OK);
	}

	@PostMapping("/enrollBatch/{userId}")
	public ResponseEntity<String> enrollBatch(@PathVariable int userId, @RequestBody Membership membership) {
		logger.debug("In enroll  batches of: " + getClass().getName());
		System.out.println(membership);
		membership.setStatus("PENDING");
		membershipServie.addMembership(membership);

		Batches batch = batchesServie.getBatchesById(membership.getBatches().getBatchId());
		Users user = userServie.getUserById(userId);
		String logdesc = user.getEmail() + " : Membership Added : " + batch.getName();
		String logDate = LocalDateTime.now().toString();
		Logs logs = new Logs(logdesc, logDate, user);
		return new ResponseEntity<String>("batch disable", HttpStatus.OK);
	}

}
