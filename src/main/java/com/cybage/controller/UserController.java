 package com.cybage.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.exception.UserNotFoundException;
import com.cybage.pojo.Logs;
import com.cybage.pojo.Role;
import com.cybage.pojo.UserRole;
import com.cybage.pojo.Users;
import com.cybage.pojo.changePasswordDTO;
import com.cybage.service.LogsServiceImpl;
import com.cybage.service.UserServieImpl; 

@RestController
@RequestMapping("/user")


public class UserController {

	Logger logger = LogManager.getLogger("UserController.class");

	@Autowired
	UserServieImpl userServie;

	@Autowired
	LogsServiceImpl logsService; 
	
	@Autowired
	private  BCryptPasswordEncoder bCyrBCryptPasswordEncoder;

	@GetMapping("/getAllUsers")
	public List<Users> getAllUsers() {
		logger.debug("In get all users Controller");
		List<Users> users = userServie.getAllUsers();
		if (users.size() == 0) {
			throw new UserNotFoundException("Doesnt have any User");
		} else
			return users;
	}



	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody Users user) {
		logger.debug("In get all users Controller");
		userServie.updateUSer(user);

		// logs
		String description = user.getEmail() + " :User Updated";
		String date = LocalDateTime.now().toString();
		Logs log = new Logs(description, date, user);

		logsService.addLog(log);
		return new ResponseEntity<String>("user updated", HttpStatus.OK);
	}

	@GetMapping("/getUserById/{id}")
	public Users getUserById(@PathVariable int id) {
		logger.debug("In get User By id");
		Users user = userServie.getUserById(id);
		if (user == null) {
			throw new UserNotFoundException("User Not Found");
		} else
			return user;
	}
	
	  @PostMapping("/")
		public Users createUser(@RequestBody Users user) throws Exception
		{
	    	//user.setProfile("default.png");
	    	//encoding password with bcryptpasswordencoder
	    	user.setPassword((this.bCyrBCryptPasswordEncoder.encode(user.getPassword())));
	    	
	    	Set<UserRole>roles= new HashSet<>();
	    	Role role = new Role();
	    	role.setRoleId(45l);
	    	role.setRoleName("Normal");
	    	UserRole  userRole= new UserRole();
	    	userRole.setUser(user);
	    	userRole.setRole(role);
	    	
	    	roles.add(userRole);
			return this.userServie.createUser(user, roles);
		}
	



	  
	  @PostMapping("/change-password")
	  public ResponseEntity<String> changePassword(@RequestBody changePasswordDTO cp)
	  {
		  System.out.println(cp.getNewPassword());
		  System.out.println(cp.getOldPassword());
		  
		  
		  return new ResponseEntity<String>("Password updated", HttpStatus.OK);
	  }
	  
	  
	  @PostMapping("/appointManager")
		public Users appointManager(@RequestBody Users user) throws Exception
		{
	    	
	    	user.setPassword((this.bCyrBCryptPasswordEncoder.encode(user.getPassword())));
	    	
	    	Set<UserRole>roles= new HashSet<>();
	    	Role role = new Role();
	    	role.setRoleId(50l);
	    	role.setRoleName("MANAGER");
	    	UserRole  userRole= new UserRole();
	    	userRole.setUser(user);
	    	userRole.setRole(role);
	    	
	    	roles.add(userRole);
			return this.userServie.createUser(user, roles);
		}

}
