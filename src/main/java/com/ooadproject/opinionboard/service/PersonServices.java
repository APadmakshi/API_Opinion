package com.ooadproject.opinionboard.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ooadproject.opinionboard.Exception.UserNotFoundException;
import com.ooadproject.opinionboard.person.Person;
import com.ooadproject.opinionboard.person.Role;
import com.ooadproject.opinionboard.repo.PersonRepo;
import com.ooadproject.opinionboard.repo.RoleRepo;

import net.bytebuddy.matcher.NullMatcher;

@Service
public class PersonServices implements UserDetailsService{
	private final PersonRepo personRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;
	// constructor class
	@Autowired
	public PersonServices(PersonRepo personRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo)
	{
		this.personRepo = personRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Person person = personRepo.findDistinctPersonByUserName(username);
		if(person==null)
		{
			throw new UsernameNotFoundException("User not found in Db");
		} else {
			
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		person.getRoles().forEach(role->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			});
		
		
		return new org.springframework.security.core.userdetails.User(person.getUserName(), person.getPassword(), authorities);
	}
	
	public Person addPerson(Person person)
	{
		//person.setPassword(passwordEncoder.encode(person.getPassword()));
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
	
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}
	
	public void addRoleToUser(String userName, String roleName)
	{
		Person person = personRepo.findDistinctPersonByUserName(userName);
		Role role= roleRepo.findByName(roleName);
		person.getRoles().add(role);
	}
	
	public Boolean findPersonByUserName(String userName)
	{
		Person person = personRepo.findDistinctPersonByUserName(userName);
		if(person!=null) {
			return true;
		}
		return false;
	}
	
	public Person findPersonByUser(String userName)
	{
		return personRepo.findDistinctPersonByUserName(userName);
	}

	
}
