package com.ooadproject.opinionboard.resources;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ooadproject.opinionboard.person.Comments;
import com.ooadproject.opinionboard.person.Friends;
import com.ooadproject.opinionboard.person.Opinion;
import com.ooadproject.opinionboard.service.CommentsServices;
import com.ooadproject.opinionboard.service.FriendsServices;
import com.ooadproject.opinionboard.service.OpinionServices;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/comments")
public class CommentResource {
	private final CommentsServices commentsServices;
	private final OpinionServices opinionServices;
	private final FriendsServices friendsServices;
	
	public CommentResource(CommentsServices commentsServices, OpinionServices opinionServices, FriendsServices friendsServices) {
		this.commentsServices = commentsServices;
		this.opinionServices = opinionServices;
		this.friendsServices = friendsServices;
	}

	@PostMapping("/addComments")
	public ResponseEntity<List<Opinion>> addComments(@RequestBody Comments comments) {
		Comments commentCreated = commentsServices.addComments(comments);
		List<Opinion> friendsOpinions = new ArrayList<>();
		Friends userName = friendsServices.findFidOfUserName(comments.getUserName());
		Friends friends = friendsServices.findFriendOfUserName(userName.getPerson().getId());
		List<Opinion> userOpinion = opinionServices.findOpinionByUserName(comments.getUserName());
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
		return new ResponseEntity<>(friendsOpinions, HttpStatus.CREATED);
	}

	@GetMapping("/findallcomments")
	public ResponseEntity<List<Comments>> findAllComments() {
		List<Comments> comments = commentsServices.findAll();
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
}
