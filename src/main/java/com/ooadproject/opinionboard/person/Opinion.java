package com.ooadproject.opinionboard.person;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data

public class Opinion implements Serializable{
	@Id
	@Column(nullable = false,updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long oid;
	@Column(nullable = false,updatable = false)
	private String username;
	private String opinionDes;
	//private Comments comments;
	@Column(updatable=false)
	private LocalDateTime dateTime;
	@Column(updatable = false)
	private Boolean isPublic;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	@JsonIgnoreProperties("opinion")
	private Person person;
	
	@OneToMany(mappedBy = "opinion", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("opinion")
	private Set<Comments> userComments = new HashSet<>();


	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOpinionDes() {
		return opinionDes;
	}

	public void setOpinionDes(String opinionDes) {
		this.opinionDes = opinionDes;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Set<Comments> getUserComments() {
		return userComments;
	}

	public void setUserComments(Set<Comments> userComments) {
		this.userComments = userComments;
	}
	
	
	
}
