package com.tour.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.entity.UserEntity;
import com.tour.exception.HolidayPoriumException;
import com.tour.model.UserDTO;
import com.tour.repository.UserRepository;
import com.tour.utility.HashingUtility;
import com.tour.validator.UserValidator;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO authenticateUser(String contactNumber, String password) throws HolidayPoriumException {
		// TODO Auto-generated method stub
		UserEntity optionalUser = userRepository.findByContactNumber(contactNumber);
		if (optionalUser == null) {
			throw new HolidayPoriumException("UserService.INVALID_CREDENTIALS");
		}

		String passwordFromDB = optionalUser.getPassword();

		if (passwordFromDB != null) {
			try {
				String hashedPassword = HashingUtility.getHashValue(password);
				if (hashedPassword.equals(passwordFromDB)) {
					UserDTO userObject = new UserDTO();
					userObject.setContactNumber(optionalUser.getContactNumber());
					userObject.setEmailId(optionalUser.getEmailId());
					userObject.setUserId(optionalUser.getUserId());
					userObject.setUserName(optionalUser.getUserName());
					return userObject;
				} else
					throw new HolidayPoriumException("UserService.INVALID_CREDENTIALS");
			} catch (NoSuchAlgorithmException e) {
				throw new HolidayPoriumException("UserService.HASH_FUNCTION_EXCEPTION");
			}

		} else
			throw new HolidayPoriumException("UserService.INVALID_CREDENTIALS");

	}

	@Override
	public String registerUser(UserDTO user) throws HolidayPoriumException {
		// TODO Auto-generated method stub
		UserValidator.validateUserForRegister(user);
		UserEntity optUser = userRepository.findByContactNumber(user.getContactNumber());
		try {
			if (optUser != null) {
				throw new HolidayPoriumException("UserService.CONTACT_NUMBER_ALREADY_EXISTS");}
			else {
				
			
				String hashedPassword = HashingUtility.getHashValue(user.getPassword());
				UserEntity userEntity = new UserEntity();
				userEntity.setContactNumber(user.getContactNumber());
				userEntity.setEmailId(user.getEmailId());
				userEntity.setPassword(hashedPassword);
				userEntity.setUserName(user.getUserName());
				userRepository.save(userEntity);
				return userEntity.getUserName();
			}
		}
			catch(NoSuchAlgorithmException e) {
				throw new HolidayPoriumException("UserService.HASH_FUNCTION_EXCEPTION");	
			}
		    catch (HolidayPoriumException e) {
			    throw new HolidayPoriumException(e.getMessage());
		    }
		    catch(Exception ex) {
	    	    throw new HolidayPoriumException("UserService.INVALID_REGISTRATION");
	        }
	
	}

}
