package com.ooadproject.opinionboard.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.Servlet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ooadproject.opinionboard.person.LoginClass;
import com.ooadproject.opinionboard.person.Person;
import com.ooadproject.opinionboard.person.Role;
import com.ooadproject.opinionboard.service.PersonServices;

import lombok.Data;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/person")
public class PersonResource {
	private static PersonServices personServices = null;
	
	public PersonResource (PersonServices personServices)
	{
		this.personServices = personServices;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> authe(@RequestBody LoginClass loginData)
	{
		String uN = loginData.getUserName();
		Person person = personServices.findPersonByUser(uN);
		if(person.getPassword().equals(loginData.getPwd())) {
			return new ResponseEntity<>(person.getUserName(),HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
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
	
	@GetMapping("/myprofile/{userName}")
	public ResponseEntity<Person> findPerson(@PathVariable("userName") String userName)
	{
		Person person = personServices.findPersonByUser(userName);
		return new ResponseEntity<>(person,HttpStatus.OK);
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
	
	@PostMapping("/addrole")
	public ResponseEntity<Role> saveRole(@RequestBody Role role)
	{
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/person/addrole").toUriString());
		return ResponseEntity.created(uri).body(personServices.saveRole(role));
	}
	
	@PostMapping("/addroletouser")
	public ResponseEntity<?> roletoUser(@RequestBody RoletoUserForm form)
	{
		personServices.addRoleToUser(form.getUserName(), form.getRoleName());
		return ResponseEntity.ok().build();
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

@Data
class RoletoUserForm {
	private String userName;
	private String roleName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
