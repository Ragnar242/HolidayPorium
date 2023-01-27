package com.tour.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.tour.exception.HolidayPoriumException;
import com.tour.model.UserDTO;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

	@Rule
	public ExpectedException ee = ExpectedException.none();
	
	@Test
	public void validatePasswordValidTest() {
		Boolean result = UserValidator.validatePassword("Scott@123");
		assertTrue(result);
	}

	@Test
	public void validatePasswordInvalidTest() {
		Boolean result = UserValidator.validatePassword("scott@123");
		assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest1() throws Exception {
		Boolean result = UserValidator.validatePassword("A123456");
		assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest2() throws Exception {
		Boolean result = UserValidator.validatePassword("a123456");
		assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest3() throws Exception {
		Boolean result = UserValidator.validatePassword("null");
		assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest4() throws Exception {
		Boolean result = UserValidator.validatePassword("45658963214785");
		assertFalse(result);
	}

	@Test
	public void validateContactNumberValidTest() {
		Boolean result = UserValidator.validateContactNumber("8420305506");
		assertTrue(result);
	}

	@Test
	public void validateContactNumberInvalidTest() {
		Boolean result = UserValidator.validateContactNumber("5678901232");
		assertFalse(result);
	}

	@Test
	public void validateContactNumberInvalidTest1() {
		Boolean result = UserValidator.validateContactNumber("98678901");
		assertFalse(result);
	}

	@Test
	public void validateContactNumberInvalidTest2() {
		Boolean result = UserValidator.validateContactNumber(null);
		assertFalse(result);
	}

	@Test
	public void validateContactNumberInvalidTest3() {
		Boolean result = UserValidator.validateContactNumber("56789ab232");
		assertFalse(result);
	}

	@Test
	public void validateUserNameValidTest() {
		Boolean result = UserValidator.validateUserName("Brett Lee");
		assertTrue(result);
	}

	@Test
	public void validateUserNameInvalidTest() {
		Boolean result = UserValidator.validateUserName(" Brett Lee");
		assertFalse(result);
	}

	@Test
	public void validateUserNameInvalidTest1() {
		Boolean result = UserValidator.validateUserName(null);
		assertFalse(result);
	}

	@Test
	public void validateUserNameInvalidTest2() {
		Boolean result = UserValidator.validateUserName("A123ndy");
		assertFalse(result);
	}

	@Test
	public void validateUserNameInvalidTest3() {
		Boolean result = UserValidator.validateUserName("@ndy");
		assertFalse(result);
	}

	@Test
	public void validateEmailIdValidTest() throws HolidayPoriumException {
		Boolean result = UserValidator.validateEmailId("Jack@gmail.com");
		assertTrue(result);
	}

	@Test
	public void validateEmailIdInvalidTest() throws HolidayPoriumException {
		Boolean result = UserValidator.validateEmailId("Tom12@gmail1.c2om");
		assertFalse(result);
	}

	@Test
	public void validateUserLoginInvalidContactNumber() throws HolidayPoriumException{
		ee.expect(HolidayPoriumException.class);
		ee.expectMessage("UserValidator.INVALID_CONTACT_NUMBER_FORMAT");
		UserValidator.validateUserForLogin("98678901", "Jack@123");
	}

	@Test
	public void validateUserLoginInvalidPassword() throws HolidayPoriumException{
		ee.expect(HolidayPoriumException.class);
		ee.expectMessage("UserValidator.INVALID_PASSWORD_FORMAT");
		UserValidator.validateUserForLogin("8420305506", "jack123");
	}
	@Test
	public void validateUserForRegisterInvalidContactNumber() throws HolidayPoriumException{
		ee.expect(HolidayPoriumException.class);
		ee.expectMessage("UserValidator.INVALID_CONTACT_NUMBER_FORMAT");
		UserDTO user =new UserDTO();
		user.setContactNumber("84563619");
		user.setPassword("Jack@123");
		user.setUserName("Jack");
		user.setEmailId("Jack@gmail.com");
		UserValidator.validateUserForRegister(user);
	}
	@Test
	public void validateUserForRegisterInvalidPassword() throws HolidayPoriumException{
		ee.expect(HolidayPoriumException.class);
		ee.expectMessage("UserValidator.INVALID_PASSWORD_FORMAT");
		UserDTO user =new UserDTO();
		user.setContactNumber("84203055706");
		user.setPassword("Jack");
		user.setUserName("Jack");
		user.setEmailId("Jack@gmail.com");
		UserValidator.validateUserForRegister(user);
	}
	@Test
	public void validateUserForRegisterInvalidUserName() throws HolidayPoriumException {
		ee.expect(HolidayPoriumException.class);
		ee.expectMessage("UserValidator.INVALID_CONTACT_NUMBER_FORMAT");
		UserDTO user =new UserDTO();
		user.setContactNumber("8456361902");
		user.setPassword("Jack@123");
		user.setUserName(" Jack Jim");
		user.setEmailId("Jack@gmail.com");
		UserValidator.validateUserForRegister(user);
	}
	@Test
	public void validateUserForRegisterInvalidEmailId() throws HolidayPoriumException{
		ee.expect(HolidayPoriumException.class);
		ee.expectMessage("UserValidator.INVALID_EMAIL_ID_FORMAT");
		UserDTO user =new UserDTO();
		user.setContactNumber("8420305572");
		user.setPassword("Jack@123");
		user.setUserName("Jack");
		user.setEmailId("Jack@gmail.com");
		UserValidator.validateUserForRegister(user);
	}

}
