package com.tour.service;

import com.tour.exception.HolidayPoriumException;
import com.tour.model.UserDTO;

public interface UserService {

	public UserDTO authenticateUser(String contactNumber, String password) throws HolidayPoriumException;

	public String registerUser(UserDTO user) throws HolidayPoriumException;
	
}
