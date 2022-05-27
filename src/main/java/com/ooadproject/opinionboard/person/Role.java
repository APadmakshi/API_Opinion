package com.ooadproject.opinionboard.person;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;


import lombok.Data;

@Data
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;
	private String name;
	
	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
	}
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
	
	
}
