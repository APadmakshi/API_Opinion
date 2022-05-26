package com.ooadproject.opinionboard.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ooadproject.opinionboard.person.Friends;
import com.ooadproject.opinionboard.service.FriendsServices;

@RestController
@RequestMapping("/friends")
public class FriendsResource {
	private final FriendsServices friendsServices;
	
	public FriendsResource(FriendsServices friendsServices)
	{
		this.friendsServices = friendsServices;
	}
	
	@GetMapping("/friendslist/{id}")
	ResponseEntity<List<String>> getFriendsOfUserName(@PathVariable("id") Long id)
	{
		Friends friends = friendsServices.findFriendOfUserName(id);
		String friend = friends.getFriendsOfUserName();
		String[] arrayFriends = friend.split(",");
		List<String> fri = new ArrayList<String>();
		for(String s: arrayFriends)
		{
			fri.add(s);
		}
		return new ResponseEntity<>(fri, HttpStatus.OK);
	}
	
	
}
