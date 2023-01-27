package com.tour.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.tour.entity.DestinationDetailsEntity;
import com.tour.entity.DestinationEntity;
import com.tour.entity.ItineraryEntity;
import com.tour.model.DestinationDTO;
import com.tour.repository.DestinationDetailsRepository;

@SpringBootTest
public class DestinationDetailsServiceTest {

		@Mock
		DestinationDetailsRepository destinationDetailsRepository;

		@InjectMocks
		DestinationDetailsServiceImpl destinationDetailsService = new DestinationDetailsServiceImpl();

		@Test
		public void validGetPackageDetails() throws Exception {

			DestinationEntity destination = new DestinationEntity();
			destination.setDestinationId("D1020");
			DestinationDetailsEntity details = new DestinationDetailsEntity();
			ItineraryEntity itinerary = new ItineraryEntity();
			itinerary.setItineraryId("Ï1050");
			details.setItinerary(itinerary);
			destination.setDestinationDetails(details);
			
			Mockito.when(destinationDetailsRepository.findDestinationDetailsByDestinationId(destination.getDestinationId())).thenReturn(destination);
			DestinationDTO expected = destinationDetailsService.destinationFeatures("D1020");
			Assertions.assertEquals( expected.getDestinationId(),destination.getDestinationId());
		}

		@Test
		public void invalidGetPackageDetails() throws Exception {
			DestinationEntity destination = new DestinationEntity();
			destination.setDestinationId("D1080");
			DestinationDetailsEntity details = new DestinationDetailsEntity();
			ItineraryEntity itinerary = new ItineraryEntity();
			itinerary.setItineraryId("Ï1050");
			details.setItinerary(itinerary);
			destination.setDestinationDetails(details);
			
			Mockito.when(destinationDetailsRepository.findDestinationDetailsByDestinationId(destination.getDestinationId())).thenReturn(destination);
			
		Exception exception = Assertions.assertThrows(Exception.class,
					() -> destinationDetailsService.destinationFeatures("D1050"));
			Assertions.assertEquals("BookingServices.INVALID_DESTINATION_ID", exception.getMessage());
		}
		
}
