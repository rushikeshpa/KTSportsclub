package com.cybage.service;

import java.util.List;

import com.cybage.exception.OfferAlreadyExistsException;
import com.cybage.pojo.Membership;
import com.cybage.pojo.Offers;

public interface IOffersService {

	// return all the offers
	public List<Offers> getAllOffers();

	// add offer
	public boolean addOffer(Offers offer) throws OfferAlreadyExistsException;

	// update offer
	public void updateOffer(Offers offer);

	// delete offer
	public void deleteOfferById(int id);

	// get offer by name
	public List<Offers> getOfferByName(String name);

	// get offer by id
	public Offers getOfferById(int id);

	// status of offer
	public void statusOfOffer(String status, int id);
	
	//like offer
	public int likeOffer(int offerId);
	
	//dislike offer
	public int disLikeOffer(int offerId);
}
