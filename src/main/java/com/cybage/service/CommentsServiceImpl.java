package com.cybage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.CommentsRepository;
import com.cybage.pojo.Comments;

@Service
public class CommentsServiceImpl implements ICommentsService {

	Logger logger = LogManager.getLogger("CommentsServiceImpl.class");

	@Autowired
	CommentsRepository commentsRepository;

	@Override
	public boolean addComments(Comments comments) {
		logger.debug("In add comments of " + getClass().getName());
		commentsRepository.save(comments);
		return true;
	}

	@Override
	public List<Comments> getAllComments() {
		logger.debug("In get All comments of" + getClass().getName());
		return commentsRepository.findAll();
	}

}
