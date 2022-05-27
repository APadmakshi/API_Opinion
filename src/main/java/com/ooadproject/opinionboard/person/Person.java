package com.ooadproject.opinionboard.person;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.annotation.Generated;
import javax.persistence.*;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "userName"}) })
public class Person implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@Column(nullable = false,updatable = false)
	private String userName;
	private String name;
	@Column(nullable = false,updatable = false)
	private String emailid;
	@Column(nullable = false,updatable = false)
	private String password;
	private LocalDate dob;
	@Column(nullable=false)
	private Boolean isPublic;
	

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("person")
	private Set<Opinion> opinions = new HashSet<>();
	
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("person")
	private Set<Friends> friends = new HashSet<>();
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	

	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	public Set<Friends> getFriends() {
		return friends;
	}
	public void setFriends(Set<Friends> friends) {
		this.friends = friends;
	}
	
	public Person(Long id, String userName, String name, String emailid, String password, LocalDate dob,
			Boolean isPublic, Collection<Role> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.emailid = emailid;
		this.password = password;
		this.dob = dob;
		this.isPublic = isPublic;
		this.roles = roles;
	}
	/*
	 * public Set<Opinion> getOpinions() { return opinions; } public void
	 * setOpinions(Set<Opinion> opinions) { this.opinions = opinions; }
	 */
	public Person(String name, 
			String emailid, 
			String userName, 
			String password, 
			Integer age, 
			LocalDate dob,
			boolean isPublic) {
		super();
		this.name = name;
		this.emailid = emailid;
		this.userName = userName;
		this.password = password;
		this.dob = dob;
		this.isPublic = isPublic;
	}
	
	public Person() {
		super();
	}
	
	
	public Person(String name, 
			String emailid, 
			String password, 
			Integer age, 
			LocalDate dob, 
			boolean isPublic) {
		super();
		this.name = name;
		this.emailid = emailid;
		this.password = password;
		this.dob = dob;
		this.isPublic = isPublic;
	}
	
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", emailid=" + emailid + ", userName=" + userName + ", password=" + password
				+ ", dob=" + dob + ", isPublic=" + isPublic + "]";
	}
	

	
}
