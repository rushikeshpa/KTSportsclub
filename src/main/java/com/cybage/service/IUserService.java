package com.cybage.service;

import java.util.List;
import java.util.Set;

import com.cybage.exception.UserAlreadyExistsException;
import com.cybage.pojo.Logs;
import com.cybage.pojo.Sports;
import com.cybage.pojo.UserRole;
import com.cybage.pojo.Users;

public interface IUserService {
	// return all the Users
	public List<Users> getAllUsers();

	// add user
	public Boolean registerUser(Users user) throws UserAlreadyExistsException;

	// update user
	public void updateUSer(Users user);

	// get User by id
	public Users getUserById(int id);

	Users createUser(Users user, Set<UserRole> userRoles) throws Exception;
	

//	// lock accounts
//	public void lockUserAccount(int userId);
//
//	// unlock accounts
//	public String unlockUserAccount(int userId, Users user);

}
