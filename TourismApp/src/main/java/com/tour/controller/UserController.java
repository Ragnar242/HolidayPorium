package com.tour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tour.model.UserDTO;
import com.tour.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("UserAPI")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	//login user - fetches and returns user details taking user object as parameter
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO dto){
		try {
			UserDTO userDTO = userService.authenticateUser(dto.getContactNumber(), dto.getPassword());
			return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,environment.getProperty(e.getMessage()));
		}
		
	}
	
	//register user - takes user details and give success/error response back after checking user details in db
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO dto){
		try {
			String response = userService.registerUser(dto);
			String concatMessageString = environment.getProperty("UserAPI.REGISTER_USER_SUCCESS1")+response+"!"+environment.getProperty("UserAPI.REGISTER_USER_SUCCESS2");
			return new ResponseEntity<String>(concatMessageString,HttpStatus.CREATED);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,environment.getProperty(e.getMessage()));
		}
		
	}

}
