package com.tour.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tour.entity.DestinationDetailsEntity;
import com.tour.entity.DestinationEntity;
import com.tour.entity.ItineraryEntity;

@DataJpaTest
public class DestinationRepositoryTest {

		@Autowired
		DestinationRepository destinationRepository;
		
		private DestinationEntity destination;
		private DestinationDetailsEntity details;
		private ItineraryEntity itinerary;
		
		@BeforeEach
		public void setUp() {
			destination=new DestinationEntity();
			destination.setDestinationId("D1020");
			destination.setAvailability(40);
			destination.setContinent("asia");
			destination.setDestinationId("D1018");
			destination.setDestinationName("India: The great Taj Mahal and Gateway of India");
			destination.setDiscount(2.0f);
			destination.setFlightCharge(2700.50f);
			destination.setImageUrl("/asserts/India.jpg");
			destination.setNoOfNights(8);
			
			details=new DestinationDetailsEntity();
			details.setDetailsId("DL116");
			details.setAbout("India as a place got many historical places to visit such as Taj Mahal");
			details.setHighlights("Monuments, temples");
			details.setPace("On this guided tour you will walk for about 1 hour daily across moderately uneven " );
			details.setPackageInclusion("3 nights dinner, 3 days breakfast, swimming pool, spa");
			
			itinerary=new ItineraryEntity();
			itinerary.setFirstDay("Morning heading to Agra");
			itinerary.setRestOfDays("Enjoying in the same spot");
			itinerary.setLastDay("Stay at Agra for 2 days and heading back to Delhi for visiting gate way of India");
			itinerary.setItineraryId("I1016");
			details.setItinerary(itinerary);
			destination.setDestinationDetails(details);

			
		}
		
		//invalid continent
		@Test
		public void invalidContinent() throws Exception{
			Assertions.assertTrue(destinationRepository.findDestinationByContinent("africa").isEmpty());
			
		}
		
		//No packages
		@Test
		public void noPackageFound() throws Exception{
			Assertions.assertNotNull(destinationRepository.findDestination());
		}
		
		//No hotdeal packages
		@Test
		public void noHotDeals() throws Exception{
			Assertions.assertNotNull(destinationRepository.findHotDeals());
		}
		
}
