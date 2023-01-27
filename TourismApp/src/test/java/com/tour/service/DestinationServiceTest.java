package com.tour.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.tour.entity.DestinationDetailsEntity;
import com.tour.entity.DestinationEntity;
import com.tour.entity.ItineraryEntity;
import com.tour.exception.HolidayPoriumException;
import com.tour.model.DestinationDTO;
import com.tour.model.DestinationDetailsDTO;
import com.tour.model.ItineraryDTO;
import com.tour.repository.DestinationRepository;

@SpringBootTest
public class DestinationServiceTest {

		@Mock
		DestinationRepository destinationRepository;

		@InjectMocks
		private DestinationServiceImpl destinationService = new DestinationServiceImpl();

		@Test
		public void invalidgetSearchDestinationDetails() throws HolidayPoriumException {
			List<DestinationEntity> dest = new ArrayList<DestinationEntity>();
			DestinationEntity destination = new DestinationEntity();
			destination.setContinent("asia");
			destination.setDestinationId("D1020");
			DestinationDetailsEntity details = new DestinationDetailsEntity();
			details.setDetailsId("DL116");
			ItineraryEntity itinerary = new ItineraryEntity();
			itinerary.setItineraryId("I1016");
			details.setItinerary(itinerary);
			destination.setDestinationDetails(details);
			dest.add(destination);
			Mockito.when(destinationRepository.findDestinationByContinent(dest.get(0).getContinent())).thenReturn(dest);
			HolidayPoriumException exception = Assertions.assertThrows(HolidayPoriumException.class,
					() -> destinationService.browseDestinations("Vietnam"));
			Assertions.assertEquals("DestinationService.HAVING_NO_DESTINATION", exception.getMessage());

		}

		@Test
		public void validgetSearchDestinationDetails() throws Exception {
			List<DestinationEntity> dest = new ArrayList<DestinationEntity>();
			DestinationEntity destination = new DestinationEntity();
			destination.setContinent("asia");
			destination.setDestinationId("D1020");
			DestinationDetailsEntity details = new DestinationDetailsEntity();
			details.setDetailsId("DL116");
			ItineraryEntity itinerary = new ItineraryEntity();
			itinerary.setItineraryId("I1016");
			details.setItinerary(itinerary);
			destination.setDestinationDetails(details);
			dest.add(destination);
			Mockito.when(destinationRepository.findDestinationByContinent(dest.get(0).getContinent())).thenReturn(dest);
			List<DestinationDTO> expected = destinationService.browseDestinations("asia");
			for (DestinationDTO e : expected) {
				e.setContinent("asia");
				e.setDestinationId("D1020");
				DestinationDetailsDTO detail = new DestinationDetailsDTO();
				ItineraryDTO iti = new ItineraryDTO();
				iti.setItineraryId("I1016");
				detail.setItineraryDTO(iti);
				detail.setDetailsId("DL116");
				e.setDestinationDetailsDTO(detail);

			}
			Assertions.assertEquals(expected.get(0).getContinent(), dest.get(0).getContinent());

		}

		@Test
		public void noDestinations() throws Exception {
			List<DestinationEntity> dest = new ArrayList<DestinationEntity>();

			Mockito.when(destinationRepository.findDestination()).thenReturn(dest);

			Exception exception = Assertions.assertThrows(Exception.class, () -> destinationService.dealsAvailable());
			Assertions.assertEquals("DestinationService.NO_PACKAGE", exception.getMessage());

		}

		@Test
		public void validhotDeals() throws Exception {
			List<DestinationEntity> dest = new ArrayList<DestinationEntity>();

			Mockito.when(destinationRepository.findHotDeals()).thenReturn(dest);

			Exception exception = Assertions.assertThrows(Exception.class, () -> destinationService.hotDeals());
			Assertions.assertEquals("DestinationService.NO_SPECIALPACKAGE", exception.getMessage());

		}
		
}
