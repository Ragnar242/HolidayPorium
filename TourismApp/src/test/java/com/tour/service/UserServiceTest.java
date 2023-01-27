package com.tour.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.tour.entity.UserEntity;
import com.tour.exception.HolidayPoriumException;
import com.tour.model.UserDTO;
import com.tour.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

		@Mock
		UserRepository userRepository;

		@InjectMocks
		UserServiceImpl userServiceImpl;

		@Test
		public void invalidLoginInvalidUser() throws Exception {
			UserEntity user = new UserEntity();

			String contactNo = "1234567890";
			String password = "abcd";

			user.setPassword("xyz");

			Mockito.when(userRepository.findByContactNumber(contactNo)).thenReturn(user);
			HolidayPoriumException exception = assertThrows(HolidayPoriumException.class,
					() -> userServiceImpl.authenticateUser(contactNo, password));
			assertEquals("UserService.INVALID_CREDENTIALS", exception.getMessage());

		}

		@Test
		public void invalidLoginNullPassword() throws Exception {
			UserEntity user = new UserEntity();

			String contactNo = "1234567890";
			String password = "abcd";

			user.setPassword(null);

			Mockito.when(userRepository.findByContactNumber(contactNo)).thenReturn(user);
			HolidayPoriumException exception = assertThrows(HolidayPoriumException.class,
					() -> userServiceImpl.authenticateUser(contactNo, password));
			assertEquals("UserService.INVALID_CREDENTIALS", exception.getMessage());

		}

		@Test
		public void invalidLoginInvalidPassword() throws Exception {
			UserEntity users = new UserEntity();
			users.setPassword("abc");
			Mockito.when(userRepository.findByContactNumber(Mockito.anyString())).thenReturn(users);
			HolidayPoriumException exception = assertThrows(HolidayPoriumException.class, ()-> userServiceImpl.authenticateUser("8884967823","Scott@123" ));
			assertEquals("UserService.INVALID_CREDENTIALS", exception.getMessage());
			
		}

		@Test
		public void validLogin() throws Exception {
			UserEntity expected = new UserEntity();
			expected.setPassword("3284cbd43ac6fc733d7c3d2176e7a52bbaeba81471702ec332a0a65689cf16e3");
			expected.setContactNumber("8884967823");
			Mockito.when(userRepository.findByContactNumber(Mockito.anyString())).thenReturn(expected);
			UserDTO actual = userServiceImpl.authenticateUser("8889765465", "Scott@123");
			assertEquals(expected.getContactNumber(), actual.getContactNumber());	}
		
		
		@Test
		public void invalidRegister() throws HolidayPoriumException {
			UserDTO users = new UserDTO();
			users.setContactNumber("9668599117");
			users.setPassword("zaidkhan660@123");
			UserEntity expected = new UserEntity();
			expected.setPassword("3284cbd43ac6fc733d7c3d2176e7a52bbaeba81471702ec332a0a65689cf16e3");
			expected.setContactNumber("9668599117");
			Mockito.when(userRepository.findByContactNumber(Mockito.anyString())).thenReturn(expected);
			HolidayPoriumException exception = assertThrows(HolidayPoriumException.class, ()->userServiceImpl.registerUser(users));
			assertEquals("UserService.CONTACT_NUMBER_ALREADY_EXISTS", exception.getMessage());
			
				}
		@Test
		public void validRegister() throws HolidayPoriumException {
			UserDTO users = new UserDTO();
			users.setContactNumber("7205583567");
			users.setPassword("zaidkhan660@123");
			users.setUserName("Zaid Khan");
			
		
			
			UserEntity expected = new UserEntity();
			expected.setPassword("khan123@12");
			expected.setContactNumber("7809616667");
			expected.setUserName("Zaid Khan");
			expected.setUserId(102);
			
			
			Mockito.when(userRepository.findByContactNumber(Mockito.anyString())).thenReturn(expected);
			 
				HolidayPoriumException exception = assertThrows(HolidayPoriumException.class, ()->userServiceImpl.registerUser(users));
				assertEquals("UserService.CONTACT_NUMBER_ALREADY_EXISTS", exception.getMessage());
			assertEquals(expected.getUserName() , users.getUserName());
	}

}
