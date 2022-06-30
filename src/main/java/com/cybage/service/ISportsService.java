package com.cybage.service;

import java.util.List;

import com.cybage.exception.SportsAlreadyExistsException;
import com.cybage.pojo.Offers;
import com.cybage.pojo.Sports;

public interface ISportsService {
	// return all the Sports
	public List<Sports> getAllSports();

	// add sports
	public boolean addSports(Sports sports) throws SportsAlreadyExistsException;

	// update sports
	public void updateSports(Sports sports);

	// delete sports
	public void deleteSportsById(int id);

	// get sports by name
	public List<Sports> getSportsByName(String name);

	// get offer by id
	public Sports getSportsById(int id);

	// status of offer
	public void statusOfSports(String status, int id);
}
