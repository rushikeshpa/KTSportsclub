package com.cybage.service;

import java.util.List;
import java.util.Set;

import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.LogsRepository;
import com.cybage.dao.UserRepository;
import com.cybage.exception.UserAlreadyExistsException;
import com.cybage.exception.UserFoundException;
import com.cybage.exception.UserNotFoundException;
import com.cybage.pojo.Logs;
import com.cybage.pojo.UserRole;
import com.cybage.pojo.Users;
 
 
@Service
public class UserServieImpl implements IUserService {

	Logger logger = LogManager.getLogger("UserServieImpl.class");

	@Autowired
	UserRepository userRepository;

	@Autowired
	LogsRepository logsRepository;
	
	@Autowired
	private IRoleService roleRepository;

	@Override
	public List<Users> getAllUsers() {
		logger.debug("in list users dao" + getClass().getName());
		return userRepository.findAll();
	}

	@Override
	public Boolean registerUser(Users user) {
		logger.debug("in register user " + getClass().getName());
		if (userRepository.existsByemail(user.getEmail())) {
			throw new UserAlreadyExistsException("User Already Exists");
		} else {
			userRepository.save(user);
			return true;
		}
	}

	@Override
	public void updateUSer(Users user) {
		logger.debug("in register user " + getClass().getName());
		userRepository.save(user);
	}

	@Override
	public Users getUserById(int id) {
		logger.debug("in get user by id " + getClass().getName());
		return userRepository.getById(id);
	}
	
	@Override
	public Users createUser(Users user, Set<UserRole> userRoles) throws Exception    {
	Users local=this.userRepository.findByname(user.getUsername());
		if(local!=null)
		{
			System.out.println("User is already there!! ");
			 throw new UserFoundException();
		}
		else
		{
			for(UserRole ur : userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local=this.userRepository.save(user);
			}
		return local;
	}

	public void updateCount(String username) {
		Users  user=userRepository.findByname(username);
		System.out.println(user);
		int count=user.getCount();
		count++;
		user.setCount(count);
		System.out.println(user);
	    userRepository.save(user);
	    }
//	@Override
//	public void lockUserAccount(int userId) {
//		// TODO Auto-generated method stub
//		Users userById = getUserById(userId);
//		userById.setIsActive("FALSE");
//
//	}
//
//	@Override
//	public String unlockUserAccount(int userId, Users user) {
//		if (user.getRole().equals("ADMIN")) {
//			Users userById = getUserById(userId);
//			userById.setIsActive("TRUE");
//			return "Account is Enabled";
//		}
//		return "Account cannot be enabled";
//	}

}
