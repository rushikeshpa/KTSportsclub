package com.cybage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.pojo.Events;

@Repository
public interface EventRepository extends JpaRepository<Events, Integer> {

}
