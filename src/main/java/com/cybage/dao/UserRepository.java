package com.cybage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.pojo.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	boolean existsByemail(String email);

	public Users findByname(String name);
	
	public Users findByemail(String email);
}
