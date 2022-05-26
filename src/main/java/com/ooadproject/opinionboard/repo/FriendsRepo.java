package com.ooadproject.opinionboard.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooadproject.opinionboard.person.Friends;

public interface FriendsRepo extends JpaRepository<Friends, Long>{
	
	Friends findFriendsOfUserNameByFid(Long id);
	Friends findNonFriendsOfUserNameByFid(Long fid);
	Friends findFidByUserName(String userName);

}
