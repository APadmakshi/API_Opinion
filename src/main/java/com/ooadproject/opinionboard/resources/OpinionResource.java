package com.ooadproject.opinionboard.resources;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ooadproject.opinionboard.person.Friends;
import com.ooadproject.opinionboard.person.Opinion;
import com.ooadproject.opinionboard.person.Person;
import com.ooadproject.opinionboard.repo.OpinionRepo;
import com.ooadproject.opinionboard.service.FriendsServices;
import com.ooadproject.opinionboard.service.OpinionServices;
import com.ooadproject.opinionboard.service.PersonServices;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/opinion")
public class OpinionResource {
	private final OpinionServices opinionServices;
	private final FriendsServices friendsServices;
	private final PersonServices personServices;
	
	public OpinionResource (OpinionServices opinionServices, FriendsServices friendsServices, PersonServices personServices)
	{
		this.opinionServices = opinionServices;
		this.friendsServices = friendsServices;
		this.personServices = personServices;
		
	}
	
	@GetMapping("/findall")
	ResponseEntity<List<Opinion>> getAllOpinion(){
		List<Opinion> opinions = opinionServices.findAll();
		return new ResponseEntity<>(opinions, HttpStatus.OK);
	}
	
	@SuppressWarnings("null")
	@PostMapping("/addopinion")
	ResponseEntity<List<Opinion>> addOpinion(@RequestBody Opinion opinions) 
	{
		String userName = opinions.getUsername();
		Person person = personServices.findPersonByUser(userName);
		opinions.setPerson(person);
		Opinion opinionsAdded = opinionServices.addOpinion(opinions);
		List<Opinion> friendsOpinions = new ArrayList<>();
		if(friendsServices.findFriendsForUserName(opinions.getPerson().getUserName()))
		{
			Friends friends = friendsServices.findFriendOfUserName(opinions.getPerson().getId());
			List<Opinion> userOpinion = opinionServices.findOpinionByUserName(opinions.getUsername());
			Collections.reverse(userOpinion);
			friendsOpinions.addAll(userOpinion);
			String friend = friends.getFriendsOfUserName();
			String[] arrayFriends = friend.split(",");
			for(String s: arrayFriends)
			{
				List<Opinion> op = opinionServices.findOpinionByUserName(s);
				Collections.reverse(op);
				friendsOpinions.addAll(op);
				Set<Opinion> set  = new LinkedHashSet<Opinion>();
				set.addAll(friendsOpinions);
				friendsOpinions.clear();
				friendsOpinions.addAll(set);
			}			
		} else {
			List<Opinion> publicOpinion = opinionServices.findAllPublicOpinions();
			List<Opinion> userOpinion = opinionServices.findOpinionByUserName(opinions.getUsername());
			friendsOpinions.addAll(publicOpinion);
			friendsOpinions.addAll(userOpinion);
			Set<Opinion> set  = new LinkedHashSet<Opinion>();
			set.addAll(friendsOpinions);
			friendsOpinions.clear();
			friendsOpinions.addAll(set);
			
		}
		
		return new ResponseEntity<>(friendsOpinions, HttpStatus.OK);
	}
	
	@GetMapping("/allopinion/{id}")
	ResponseEntity<List<Opinion>> addOpinion(@PathVariable("id") Long id) 
	{
		List<Opinion> friendsOpinions = new ArrayList<>();
		Friends friends = friendsServices.findFriendOfUserName(id);
		List<Opinion> userOpinion = opinionServices.findOpinionByUserName(friends.getUserName());
		Collections.reverse(userOpinion);
		friendsOpinions.addAll(userOpinion);
		String friend = friends.getFriendsOfUserName();
		String nonFriend = friends.getNonFriendsOfUserName();
		String[] arrayFriends = friend.split(",");
		String[] arrayNoFriends = nonFriend.split(",");
		for(String s: arrayFriends)
		{
			List<Opinion> op = opinionServices.findOpinionByUserName(s);
			Collections.reverse(op);
			friendsOpinions.addAll(op);
		}
		for(String st: arrayNoFriends)
		{
			List<Opinion> op = opinionServices.findOpinionByUserName(st);
			Collections.reverse(op);
			for(Opinion o: op)
			{
			if(o.getIsPublic())
			{
				friendsOpinions.add(o);
			}
			}
		}
		return new ResponseEntity<>(friendsOpinions, HttpStatus.OK);
	}
	
	@GetMapping("/findall/{username}")
	public ResponseEntity<List<Opinion>> getAllOpinionOfUser(@PathVariable("username") String userName)
	{
		List<Opinion> userOpinionAll = opinionServices.findOpinionByUserName(userName);
		return new ResponseEntity<>(userOpinionAll, HttpStatus.OK);
	}
}
