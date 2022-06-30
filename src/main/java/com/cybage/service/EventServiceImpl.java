package com.cybage.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cybage.dao.EventRepository;
import com.cybage.pojo.Events;

@Service
public class EventServiceImpl implements IEventsService{

	Logger logger = LogManager.getLogger("EventServiceImpl.class");	
	
	@Autowired
	EventRepository eventRepository;
	
	@Override
	public List<Events> getAllEvents() {
		return eventRepository.findAll();
	}
	
	
}
