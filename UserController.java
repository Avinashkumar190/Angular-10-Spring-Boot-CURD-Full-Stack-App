package com.lara.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lara.entity.User;
import com.lara.exception.ResourceNotFoundException;
import com.lara.repository.UserRepository;

@RestController
//@Component
@CrossOrigin
@RequestMapping("/api/v1/")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	//Get all user
	@GetMapping("/user")
	List<User> getAllUser(){
		return userRepository.findAll();
	}

	//create user rest api
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	//Get user by id rest api
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getById(@PathVariable Integer id) {
		//public ResponseEntity<User> getById(@RequestHeader Integer id) {
		User user =userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exist with id:"+ id));
		return ResponseEntity.ok(user);
	}

	//update user rest api
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
		User user =userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exist with id:"+ id));
		user.setFirstName(userDetails.getFirstName());
		user.setSurName(userDetails.getSurName());
		user.setPincode(userDetails.getPincode());
		user.setDob(userDetails.getDob());
		user.setJoiningDate(userDetails.getJoiningDate());
		User updateUser = userRepository.save(user);
		return ResponseEntity.ok(updateUser);
	}

	//delete user rest api
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Integer id){
		User user =userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exist with id:"+ id));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@RequestMapping("/api")
	public String Test() {
		return "Welcome Neosoft Technology Angular10...";
	}

	// Search By first Name
	/*@GetMapping("/getbyfirstname/{firstName}")
		public List<User> getUserbyfirstame(@PathVariable String firstName) { 
			return userRepository.findByFirstname(firstName);
		}*/
	@GetMapping("/user/search")
	public List<User> searchUser(@RequestParam(required=false) String firstName,
			@RequestParam(required=false) String surName,
			@RequestParam(required=false) String pincode){
		return userRepository.findByFirstNameOrSurNameOrPincode(firstName,surName,pincode);
	}

	/*
	 * // Search By Last Name
	 * 
	 * @GetMapping("/getbylastname/{surName}") public List<User>
	 * searchUserbylastName(@PathVariable String surName) { return
	 * userRepository.findByLastname(surName); }
	 * 
	 * // Search By Pincode
	 * 
	 * @GetMapping("/getbypincode/{pincode}") public List<User>
	 * searchUserbyPinCode(@PathVariable String pincode) { return
	 * userRepository.findByPincode(pincode); }
	 */

	// Sorting Dob
	@GetMapping("/user/sortbydob")
	public List<User> sortUserbydob() {
		return userRepository.findByOrderByDobAsc();
	}

	// Sorting Joining Date
	@GetMapping("/user/sortbyjoiningdate")
	public List<User> sortUserbyjoiningdate() {
		return userRepository.findByOrderByJoiningDateAsc();
	}

}
