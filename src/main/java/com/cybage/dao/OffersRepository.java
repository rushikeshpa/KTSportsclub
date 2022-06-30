package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.pojo.Offers;

@Repository
@Transactional
public interface OffersRepository extends JpaRepository<Offers, Integer> {

	List<Offers> getByname(String name);

	@Modifying
	@Query("UPDATE Offers o SET o.isActive = :status WHERE o.offerId = :id")
	void statusOfOffer(@Param("status") String status, @Param("id") int id);
	
	@Query("SELECT COUNT(o.likes) FROM Offers o WHERE o.offerId = :offerId")
	int getLikesOfOfferById(@Param("offerId") int offerId);
	
	@Modifying
	@Query("UPDATE Offers o SET o.likes = :likes WHERE o.offerId= :id")
	int likeOffer(@Param("likes") int likes,@Param("id") int offerId);
	
	
	
}
