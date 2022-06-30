package com.cybage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.SportsRepository;
import com.cybage.exception.SportsAlreadyExistsException;
import com.cybage.pojo.Sports;

@Service
public class SportsServieImpl implements ISportsService {

	Logger logger = LogManager.getLogger("SportsServieImpl.class");

	@Autowired
	SportsRepository sportsRepository;

	@Override
	public List<Sports> getAllSports() {
		logger.debug("in list sports dao");
		return sportsRepository.findAll();
	}

	@Override
	public boolean addSports(Sports sports) {
		logger.debug("in list sports dao");
		if (sportsRepository.existsBysportName(sports.getSportName())) {
			throw new SportsAlreadyExistsException("Sports Already Exists");
		} else {
			sportsRepository.save(sports);
			return true;
		}

	}

	@Override
	public void updateSports(Sports sports) {
		logger.debug("in list sports dao");
		sportsRepository.save(sports);
	}

	@Override
	public void deleteSportsById(int id) {
		logger.debug("in list sports dao");
		sportsRepository.deleteById(id);
	}

	@Override
	public List<Sports> getSportsByName(String name) {
		logger.debug("in get sports by name sports dao");
		List<Sports> list = sportsRepository.getBysportName(name);
		return list;
	}

	@Override
	public Sports getSportsById(int id) {
		logger.debug("in get sports by id sports dao");
		return sportsRepository.getById(id);
	}

	@Override
	public void statusOfSports(String status, int id) {
		logger.debug("In status of sports: " + getClass().getName());
		sportsRepository.statusOfSports(status, id);
	}
}
