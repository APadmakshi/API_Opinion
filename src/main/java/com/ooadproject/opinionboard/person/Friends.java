package com.ooadproject.opinionboard.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Friends {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fid;
	private String userName;
	private String friendsOfUserName;
	private String nonFriendsOfUserName;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	@JsonIgnoreProperties("friends")
	private Person person;
	
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFriendsOfUserName() {
		return friendsOfUserName;
	}
	public void setFriendsOfUserName(String friendsOfUserName) {
		this.friendsOfUserName = friendsOfUserName;
	}
	public String getNonFriendsOfUserName() {
		return nonFriendsOfUserName;
	}
	public void setNonFriendsOfUserName(String nonFriendsOfUserName) {
		this.nonFriendsOfUserName = nonFriendsOfUserName;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	
	
}
