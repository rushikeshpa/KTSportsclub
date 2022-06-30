package com.cybage.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.pojo.Role;
 

public interface IRoleService extends JpaRepository<Role, Long>  {

}
