package com.cybage.service;

import java.util.List;

import com.cybage.pojo.Comments;

public interface ICommentsService {

	// add comments
	boolean addComments(Comments comments);
	
	//get All comments
	List<Comments> getAllComments();
}
