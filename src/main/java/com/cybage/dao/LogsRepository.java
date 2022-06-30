package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cybage.pojo.Logs;

public interface LogsRepository extends JpaRepository<Logs, Integer> {

//	@Query(value = "SELECT * FROM Logs WHERE logId = :id", nativeQuery = true)
	@Query("SELECT l FROM Logs l WHERE l.users.userId = :id")
	List<Logs> getLogsById(@Param("id") int id);

	@Query("SELECT l FROM Logs l WHERE l.users.name = :name")
	List<Logs> getLogsByName(@Param("name") String name);
}
