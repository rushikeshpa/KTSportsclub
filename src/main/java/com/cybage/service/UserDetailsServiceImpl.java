package com.cybage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cybage.dao.UserRepository;
import com.cybage.exception.UserDisableException;
import com.cybage.pojo.Users;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Users user = this.userRepository.findByname(name);
		
		if(user==null)
		{
		
            System.out.println("User not Found");
			throw new UsernameNotFoundException("No user Found!!!");
		}
		else if(user.getCount()>=2)
			
		{
			user.isAccountNonLocked();
			
		}
		else
		{
		
		return user;

		}
		return user;
	}
	}
