package com.ooadproject.opinionboard.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooadproject.opinionboard.person.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Long>{

}
