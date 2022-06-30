package com.cybage.service;

import java.util.List;

import com.cybage.pojo.Logs;

public interface ILogsService {

	// get all logs
	public List<Logs> getAllLogs();

	// gertAll logs for perticular userid
	public List<Logs> getLogsById(int id);

	// add log
	public void addLog(Logs logs);

	// gertAll logs for perticular userid
	public List<Logs> getLogsByName(String name);

}
