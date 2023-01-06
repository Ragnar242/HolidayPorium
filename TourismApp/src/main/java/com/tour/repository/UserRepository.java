package com.tour.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tour.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer>{

	@Query("select u from User u where u.contactNumber=?1")
	public UserEntity findByContactNumber(String contactNumber);
	
}
