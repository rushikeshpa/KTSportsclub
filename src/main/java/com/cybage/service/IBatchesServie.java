package com.cybage.service;

import java.util.List;

import com.cybage.exception.BatchAlreadyExistsException;
import com.cybage.pojo.Batches;

public interface IBatchesServie {
	// return all the Batches
	public List<Batches> getAllBatches();
	
	// add batches
	public boolean addBatch(Batches batch) throws BatchAlreadyExistsException;
	
	//update batches
	public void updateBatch(Batches batch);
	
	//delete batches
	public void deleteBatchesById(int id);
	
	// get batches by name
	public List<Batches> getBatchByName(String name);
	
	// get batches by id
	public Batches getBatchesById(int id);
	
	//status of batch
	public void statusOfBatch(String status,int id);
}
