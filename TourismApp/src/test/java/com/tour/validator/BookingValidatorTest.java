package com.tour.validator;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class BookingValidatorTest {

	@Rule
	public ExpectedException ee = ExpectedException.none();
	
	// valid trip start date test 
	@Test
	public void validValidateTripStartDate() throws Exception{
		LocalDate d=LocalDate.now();
		LocalDate checkIn=d.plusDays(1);
		Boolean result=BookingValidator.validateTripStartDate(checkIn);
		Assert.assertTrue(result);
	}
	
	//invalid trip start dates tests
	@Test
	public void invalidValidateTripStartDate2() throws Exception{
		Boolean result=BookingValidator.validateTripStartDate(null);
		Assert.assertFalse(result);
	}
	//valid noOfPassengers test
	@Test
	public void validValidateNoOfPassengers() throws Exception{
		Boolean result=BookingValidator.validateNumberOfTravellers(2);
		Assert.assertTrue(result);
	}
	//invalid noOfPassengers test case of 6,0 and null
	@Test
	public void invalidValidateNofPassengers() throws Exception{
		Boolean result=BookingValidator.validateNumberOfTravellers(6);
		Assert.assertFalse(result);
	}
	@Test
	public void invalidValidateNofPassengers2() throws Exception{
		Boolean result=BookingValidator.validateNumberOfTravellers(0);
		Assert.assertFalse(result);
	}
	@Test
	public void invalidValidateNofPassengers3() throws Exception{
		Boolean result=BookingValidator.validateNumberOfTravellers(null);
		Assert.assertFalse(result);
	}
	
}
