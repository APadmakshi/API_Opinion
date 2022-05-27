package com.ooadproject.opinionboard.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooadproject.opinionboard.person.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
	Role findByName(String name);

}
