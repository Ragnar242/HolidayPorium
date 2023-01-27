package com.tour.validator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class DestinationValidatorTest {

	@Rule
	public ExpectedException ee= ExpectedException.none();
	
	//Test case for ValidContinet of SearchValidator
	@Test
	public void validateContinentValidTest() {
		Boolean result = DestinationValidator.validateContinent("Europe");
		Assert.assertTrue(result);
	}
	
	@Test
	public void validateContinentValidTest2() {
			Boolean result = DestinationValidator.validateContinent("europe");
			Assert.assertTrue(result);
	}
	//Test case for InValidContinet of SearchValidator
	@Test
	public void validateContinentInValidTest() {
		Boolean result = DestinationValidator.validateContinent("India");
		Assert.assertFalse(result);
	}
	@Test
	public void validateContinentInValidTest2() {
		Boolean result = DestinationValidator.validateContinent("");
		Assert.assertFalse(result);
	}
	@Test
	public void validateForSearch() throws Exception{
		ee.expect(Exception.class);
		ee.expectMessage("SearchValidator.INVALID_CONTINENT");
		Assertions.assertThrows(Exception.class ,()->DestinationValidator.validateForSearch("India")); 
	}
	
}
