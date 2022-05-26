package com.ooadproject.opinionboard.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooadproject.opinionboard.person.Person;

public interface PersonRepo extends JpaRepository<Person, String>{

	void deletePersonByUserName(String userName);

	Person findDistinctPersonByUserName(String userName);
	

}
