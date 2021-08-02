package com.lara.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lara.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	//public List<User> findByFirstname(String firstName);

	//public List<User> findByLastname(String lastName);

	//public List<User> findByPincode(String pincode);
	
	List<User> findByFirstNameOrSurNameOrPincode(String firstName, String lastName,String pincode);

	public List<User> findByOrderByJoiningDateAsc(); 

	public List<User> findByOrderByDobAsc();

}
