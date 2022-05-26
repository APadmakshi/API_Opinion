package com.ooadproject.opinionboard.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooadproject.opinionboard.Exception.UserNotFoundException;
import com.ooadproject.opinionboard.person.Person;
import com.ooadproject.opinionboard.repo.PersonRepo;import net.bytebuddy.matcher.NullMatcher;

@Service
public class PersonServices {
	private final PersonRepo personRepo;
	
	// constructor class
	@Autowired
	public PersonServices(PersonRepo personRepo)
	{
		this.personRepo = personRepo;
	}
	
	public Person addPerson(Person person)
	{
		return personRepo.save(person);
	}
	
	public List<Person> findAllPersons()
	{
		return personRepo.findAll();
	}
	
	public Person updatePerson(Person person)
	{
		return personRepo.save(person);
	}
	
	public void deletePerson(String userName)
	{
		personRepo.deletePersonByUserName(userName);
	}
	
	public Boolean findPersonByUserName(String userName)
	{
		Person person = personRepo.findDistinctPersonByUserName(userName);
		if(person!=null) {
			return true;
		}
		return false;
	}
	
}
