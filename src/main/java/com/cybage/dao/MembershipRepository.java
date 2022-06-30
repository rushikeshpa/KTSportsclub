package com.cybage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.pojo.Membership;

@Repository
@Transactional
public interface MembershipRepository extends JpaRepository<Membership, Integer> {

	@Query("SELECT m FROM Membership m WHERE m.users.userId = :id")
	Membership getMemmbershipBYUserId(@Param("id") int id);
	
	@Modifying
	@Query("UPDATE Membership m SET m.status = :status WHERE m.users.userId = :id")
	void changeStatus(@Param("status") String status,@Param("id") int id);
}
