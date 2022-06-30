package com.cybage.service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.LogsRepository;
import com.cybage.pojo.Logs;

@Service
public class LogsServiceImpl implements ILogsService {

	Logger logger = LogManager.getLogger("LogsServiceImpl.class");

	@Autowired
	LogsRepository logsRepository;

	@Override
	public List<Logs> getAllLogs() {
		logger.debug("get All logs of" + getClass().getName());
		return logsRepository.findAll();
	}

	@Override
	public void addLog(Logs logs) {
		logsRepository.save(logs);
	}

	@Override
	public List<Logs> getLogsById(int id) {
		logger.debug("get All logs by id" + getClass().getName());
		return logsRepository.getLogsById(id);
	}

	@Override
	public List<Logs> getLogsByName(String name) {
		logger.debug("get All logs by name" + getClass().getName());
		return logsRepository.getLogsByName(name);
	}
}
