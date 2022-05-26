package com.ooadproject.opinionboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooadproject.opinionboard.person.Comments;
import com.ooadproject.opinionboard.repo.CommentsRepo;

@Service
public class CommentsServices {
	private final CommentsRepo commnetsRepo;
	
	@Autowired
	public CommentsServices(CommentsRepo commentsRepo)
	{
		this.commnetsRepo = commentsRepo;
	}
	
	public Comments addComments(Comments comments)
	{
		return commnetsRepo.save(comments);
	}
	
	public List<Comments> findAll()
	{
		return commnetsRepo.findAll();
	}
}
