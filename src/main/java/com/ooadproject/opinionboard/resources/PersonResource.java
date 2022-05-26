package com.ooadproject.opinionboard.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ooadproject.opinionboard.person.Person;
import com.ooadproject.opinionboard.service.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonResource {
	private static PersonServices personServices = null;
	
	public PersonResource (PersonServices personServices)
	{
		this.personServices = personServices;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Person>> getAllPersons(){
		List<Person> person = personServices.findAllPersons();
		return new ResponseEntity<>(person, HttpStatus.OK);
	}
	
	@GetMapping("/find/{userName}")
	public ResponseEntity<Boolean> getAllPersons(@PathVariable("userName") String userName){
		Boolean persons = personServices.findPersonByUserName(userName);
		return new ResponseEntity<>(persons, HttpStatus.OK);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Boolean> addUser(@RequestBody Person person)
	{
		boolean checkUserName = personServices.findPersonByUserName(person.getUserName());
		if(!checkUserName) {
		personServices.addPerson(person);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Boolean> updateUser(@RequestBody Person person)
	{
		boolean checkUserName = personServices.findPersonByUserName(person.getUserName());
		if(checkUserName) {
		personServices.updatePerson(person);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping("/deleteUser/{userName}")
	public ResponseEntity<?> deleteUser(@PathVariable("userName") String userName)
	{
		boolean checkUserName = personServices.findPersonByUserName(userName);
		if(checkUserName) {
		personServices.deletePerson(userName);
		return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
