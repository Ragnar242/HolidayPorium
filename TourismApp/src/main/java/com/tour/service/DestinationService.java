package com.tour.service;

import java.util.List;

import com.tour.exception.HolidayPoriumException;
import com.tour.model.DestinationDTO;

public interface DestinationService {

	public List<DestinationDTO> browseDestinations(String continent) throws HolidayPoriumException;

	public List<DestinationDTO>dealsAvailable() throws HolidayPoriumException;
	
	public List<DestinationDTO>hotDeals() throws HolidayPoriumException;
	
}
