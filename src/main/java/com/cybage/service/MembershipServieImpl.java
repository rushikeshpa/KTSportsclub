package com.cybage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.MembershipRepository;
import com.cybage.pojo.Membership;

@Service
public class MembershipServieImpl implements IMembershipService {

	Logger logger = LogManager.getLogger("MembershipServieImpl.class");

	@Autowired
	MembershipRepository membershipRepository;

	@Override
	public List<Membership> getAllMemberships() {
		logger.debug("in list membership dao");
		return membershipRepository.findAll();
	}

	@Override
	public Membership getMembershipById(int id) {
		logger.debug("In get Membership by id Dao");
		return membershipRepository.getById(id);
	}

	@Override
	public void addMembership(Membership membership) {
		logger.debug("In add membership of " + getClass().getName());
		membershipRepository.save(membership);
	}
	
	@Override
	public void changeStatus(String status,int id) {
		logger.debug("In change status membership of "+getClass().getName());
		membershipRepository.changeStatus(status, id);
		
		
	}
}
