package com.ooadproject.opinionboard.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooadproject.opinionboard.person.Friends;
import com.ooadproject.opinionboard.person.Opinion;
import com.ooadproject.opinionboard.repo.OpinionRepo;

@Service
public class OpinionServices {
	private final OpinionRepo opinionRepo;
	
	@Autowired
	public OpinionServices(OpinionRepo opinionRepo)
	{
		this.opinionRepo = opinionRepo;
	}
	
	public Opinion addOpinion(Opinion opinion)
	{
		return opinionRepo.save(opinion);
	}
	
	public List<Opinion> findAll(){
		List<Opinion>  opinions = opinionRepo.findAll();
		return opinionRepo.findAll();
	}
	
	
	public List<Opinion> findOpinionByUserName(String userName) 
	{
		return opinionRepo.findOpinionByUsername(userName); 
	}
	

	
	
}
