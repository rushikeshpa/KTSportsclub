package com.cybage.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dao.LogsRepository;
import com.cybage.pojo.Logs;
import com.cybage.service.LogsServiceImpl;

@RestController

@RequestMapping("/logs")
public class LogsController {
	Logger logger = LogManager.getLogger("LogsController.class");

	@Autowired
	LogsServiceImpl logsService;
	
	@Autowired
	LogsRepository logsRepository;

	@GetMapping("/getLogs")
	public List<Logs> getAllLogs() {
		logger.debug("In get Logs of: " + getClass().getName());
		System.out.println(logsService.getLogsById(1));
		return logsService.getAllLogs();
	}

	@GetMapping("/getLogs/{id}")
	public List<Logs> getLogsById(@PathVariable int id) {
		logger.debug("In get Logs by id of: " + getClass().getName());
		return logsService.getLogsById(id);
	}

	@GetMapping("/getLogsByName/{name}")
	public List<Logs> getLogsByName(@PathVariable String name) {
		logger.debug("In get Logs by Name of: " + getClass().getName());

		return logsService.getLogsByName(name);
	}

}
