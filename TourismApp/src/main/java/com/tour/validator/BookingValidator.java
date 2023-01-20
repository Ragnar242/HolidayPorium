package com.tour.validator;

import java.time.LocalDate;

import com.tour.exception.HolidayPoriumException;
import com.tour.model.BookingDTO;

public class BookingValidator {

	public static boolean validateTripStartDate(LocalDate checkIn) {
		if(checkIn==null) {
			return false;
		}
		LocalDate today=LocalDate.now();
		if(today.isBefore(checkIn)) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean validateNumberOfTravellers(Integer noOfPeople) {
		if(noOfPeople==null) {
			return false;
		}
		if(noOfPeople>=1 && noOfPeople<=5) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void validateBooking(BookingDTO booking) throws HolidayPoriumException{
		if(!validateNumberOfTravellers(booking.getNoOfPeople())) {
			throw new HolidayPoriumException("Booking.INVALID_CREDENTIALS");
		}
		if(!validateTripStartDate(booking.getCheckIn())) {
			throw new HolidayPoriumException("Booking.INVALID_CREDENTIALS");
		}
	}
	
}
