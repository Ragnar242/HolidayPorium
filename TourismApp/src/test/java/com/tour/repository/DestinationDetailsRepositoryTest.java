package com.tour.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DestinationDetailsRepositoryTest {

		@Autowired
		DestinationDetailsRepository destinationDetailsRepository;
		
		//valid destination ID
		@Test
		public void validPackage() throws Exception{
			Assertions.assertNotNull(destinationDetailsRepository.findDestinationDetailsByDestinationId("D1001"));
			
		}
		
		//Invalid destination ID
		@Test
		public void invalidPackage() throws Exception{
			Assertions.assertNull(destinationDetailsRepository.findDestinationDetailsByDestinationId("D1022"));
		}
		
}
