package com.cybage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.OffersRepository;
import com.cybage.exception.OfferAlreadyExistsException;
import com.cybage.pojo.Offers;

@Service
public class OffersServieImpl implements IOffersService {

	Logger logger = LogManager.getLogger("OffersServieImpl.class");

	@Autowired
	OffersRepository offersRepository;

	@Override
	public List<Offers> getAllOffers() {
		logger.debug("in list offers dao");
		return offersRepository.findAll();
	}

	@Override
	public boolean addOffer(Offers offer) {
		logger.debug("in add offers dao");
		if (offersRepository.existsById(offer.getOfferId())) {
			throw new OfferAlreadyExistsException("Offer Already Exists");

		} else {
			offersRepository.save(offer);
			return true;

		}

	}

	@Override
	public void updateOffer(Offers offer) {
		logger.debug("in update offers dao");
		offersRepository.save(offer);
	}

	@Override
	public void deleteOfferById(int id) {
		logger.debug("in delete offers dao");
		offersRepository.deleteById(id);

	}

	@Override
	public List<Offers> getOfferByName(String name) {
		logger.debug("in offer BY Name offers dao");
		List<Offers> offers = offersRepository.getByname(name);
		return offers;
	}

	@Override
	public Offers getOfferById(int id) {
		logger.debug("In get Offers by id Dao");
		return offersRepository.getById(id);
	}

	@Override
	public void statusOfOffer(String status, int id) {
		logger.debug("In status of offer: " + getClass().getName());
		offersRepository.statusOfOffer(status, id);

	}
	
	@Override
	public int likeOffer(int offerId) {
	int count = offersRepository.getLikesOfOfferById(offerId);
	count = count +1;
	offersRepository.likeOffer(count, offerId);
	return count;
	}
	
	@Override
	public int disLikeOffer(int offerId) {
		int count = offersRepository.getLikesOfOfferById(offerId);
		count = count - 1;
		offersRepository.likeOffer(count, offerId);
		return count;
	}

}
