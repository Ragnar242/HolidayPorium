package com.tour.validator;

import com.tour.exception.HolidayPoriumException;

public class DestinationValidator {

	public static void validateForSearch(String continent) throws HolidayPoriumException{
		if(!validateContinent(continent))
			throw new HolidayPoriumException("SearchValidator.INVALID_CONTINENT");			
		}
	
	public static boolean validateContinent(String continent) {
		String [] continents= {"Africa","Asia","Australia","North America","Europe","South America","Antartica"};
		for(int i=0;i<continents.length;i++) {
			if(continents[i].toLowerCase().equals(continent.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
}
