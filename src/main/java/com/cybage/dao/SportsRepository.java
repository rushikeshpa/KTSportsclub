package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.pojo.Sports;

@Repository
@Transactional
public interface SportsRepository extends JpaRepository<Sports, Integer> {

	List<Sports> getBysportName(String name);

	boolean existsBysportName(String name);

	@Modifying
	@Query("UPDATE Sports s SET s.isActive = :status WHERE s.sportId = :id")
	void statusOfSports(@Param("status") String status, @Param("id") int id);

}
