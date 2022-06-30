package com.cybage.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.pojo.Events;
import com.cybage.service.EventServiceImpl;

@RestController

@RequestMapping("events")
public class EventController {

	Logger logger = LogManager.getLogger("EventController.class");

	@Autowired
	EventServiceImpl eventService;

	@GetMapping("/getAllEvents")
	public List<Events> getAllEvents() {
		return eventService.getAllEvents();

	}

}
