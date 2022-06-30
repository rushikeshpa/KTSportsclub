package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.pojo.Batches;

@Repository
@Transactional
public interface BatchesRepository extends JpaRepository<Batches, Integer> {

	List<Batches> getByname(String name);

	boolean existsBySportsSportId(int id);

	@Query("SELECT b.name FROM Batches b WHERE b.sports.sportId = :id ")
	List<String> getNameBySports(@Param("id") int id);

	@Modifying
	@Query("UPDATE Batches b SET b.isActive = :status WHERE b.batchId = :id")
	void statuOfBatch(@Param("status") String status, @Param("id") int id);
}
