package com.cybage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.BatchesRepository;
import com.cybage.dao.SportsRepository;
import com.cybage.exception.BatchAlreadyExistsException;
import com.cybage.pojo.Batches;
import com.cybage.pojo.Sports;

@Service
public class BatchesServieImpl implements IBatchesServie {

	Logger logger = LogManager.getLogger("BatchesServieImpl.class");

	@Autowired
	BatchesRepository batchesRepository;
	@Autowired
	SportsRepository sportsrepository;

	@Override
	public List<Batches> getAllBatches() {
		logger.debug("in list batches dao");
		return batchesRepository.findAll();
	}

	@Override
	public boolean addBatch(Batches batch) {
		logger.debug("in add batch dao");
		Sports sports = sportsrepository.getById(batch.getSports().getSportId());
		List<String> list = batchesRepository.getNameBySports(batch.getSports().getSportId());
		if (batchesRepository.existsBySportsSportId(batch.getSports().getSportId()) && list.contains(batch.getName())) {
			throw new BatchAlreadyExistsException(
					"Batch: " + batch.getName() + " already exist with sports: " + sports.getSportName());
		} else {
			batchesRepository.save(batch);
			return true;
		}
	}

	@Override
	public void updateBatch(Batches batch) {
		logger.debug("In update Batch Dao");
		batchesRepository.save(batch);
	}

	@Override
	public void deleteBatchesById(int id) {
		logger.debug("In delete batch Dao");
		batchesRepository.deleteById(id);

	}

	@Override
	public List<Batches> getBatchByName(String name) {
		logger.debug("In get Batches by name Dao");
		return batchesRepository.getByname(name);
	}

	@Override
	public Batches getBatchesById(int id) {
		logger.debug("In get Batch by id Dao");
		return batchesRepository.getById(id);
	}

	@Override
	public void statusOfBatch(String status, int id) {
		logger.debug("In status of batch: " + getClass().getName());
		batchesRepository.statuOfBatch(status, id);
	}
}
