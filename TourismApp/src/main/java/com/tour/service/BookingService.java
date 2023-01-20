package com.tour.service;

import java.util.List;

import com.tour.exception.HolidayPoriumException;
import com.tour.model.BookingDTO;

public interface BookingService {
	
	public BookingDTO bookPackage(BookingDTO booking) throws HolidayPoriumException;

	public List<BookingDTO> bookingVision(Integer userId) throws HolidayPoriumException;

}
