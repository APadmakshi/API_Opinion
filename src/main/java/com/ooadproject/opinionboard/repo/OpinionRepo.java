package com.ooadproject.opinionboard.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooadproject.opinionboard.person.Opinion;

public interface OpinionRepo extends JpaRepository<Opinion, Long> {
	
	List<Opinion> findOpinionByUsername(String userName);
	List<Opinion> findOpinionsByIsPublic(Boolean isPublic);

}
