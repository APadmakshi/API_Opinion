package com.ooadproject.opinionboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooadproject.opinionboard.person.Friends;
import com.ooadproject.opinionboard.repo.FriendsRepo;

@Service
public class FriendsServices {
	private final FriendsRepo friendsRepo;
	
	@Autowired
	public FriendsServices(FriendsRepo friendsrepo)
	{
		this.friendsRepo = friendsrepo;
	}
	
	public boolean findFriendsForUserName(String userName)
	{
		Friends friends = friendsRepo.findFidByUserName(userName);
		if(friends == null)
		{
			return false;
		}
		return true;
	}
	public Friends findFriendOfUserName(Long id)
	{
		Friends friends = friendsRepo.findFriendsOfUserNameByFid(id);
		return friendsRepo.findFriendsOfUserNameByFid(id);
	}
	
	public Friends findNonFriendOfUserName(Long id)
	{
		return friendsRepo.findNonFriendsOfUserNameByFid(id);
	}
	
	public List<Friends> findAll()
	{
		return friendsRepo.findAll();
	}

	public Friends findFidOfUserName(String userName) {
		return friendsRepo.findFidByUserName(userName);
	}
}
