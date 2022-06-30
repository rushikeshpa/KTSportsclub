package com.cybage.service;

import java.util.List;

import com.cybage.pojo.Membership;

public interface IMembershipService {

	// return all the Memberships
	public List<Membership> getAllMemberships();

	// get Membership by id
	public Membership getMembershipById(int id);

	// add Membership
	public void addMembership(Membership membership);
	
	//update status
	public void changeStatus(String status,int id);
}
