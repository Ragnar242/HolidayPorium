package com.tour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.entity.DestinationDetailsEntity;
import com.tour.entity.DestinationEntity;
import com.tour.entity.ItineraryEntity;
import com.tour.model.DestinationDTO;
import com.tour.model.DestinationDetailsDTO;
import com.tour.model.ItineraryDTO;
import com.tour.repository.DestinationDetailsRepository;

import jakarta.transaction.Transactional;

@Service(value = "destinationDetailsService")
@Transactional
public class DestinationDetailsServiceImpl implements DestinationDetailsService{

	@Autowired
	private DestinationDetailsRepository destinationDetailsRepository;

	// This method fetches and returns the destination details by taking destination
	// id as parameter.
	@Override
	public DestinationDTO destinationFeatures(String destinationId) throws Exception {
		// TODO Auto-generated method stub
		DestinationDTO destination = null;
		DestinationEntity destinations = destinationDetailsRepository.findDestinationDetailsByDestinationId(destinationId);
		if (destinations == null) {
			throw new Exception("BookingServices.INVALID_DESTINATION_ID");
		} else {

			destination = new DestinationDTO();
			destination.setAvailability(destinations.getAvailability());
			destination.setChargePerPerson(destinations.getChargePerPerson());
			destination.setContinent(destinations.getContinent());
			destination.setDestinationId(destinations.getDestinationId());
			destination.setDestinationName(destinations.getDestinationName());
			destination.setDiscount(destinations.getDiscount());
			destination.setFlightCharge(destinations.getFlightCharge());
			destination.setImageUrl(destinations.getImageUrl());
			destination.setNoOfNights(destinations.getNoOfNights());

			DestinationDetailsEntity detailsEntity = destinations.getDestinationDetails();
			DestinationDetailsDTO details = new DestinationDetailsDTO();
			details.setAbout(detailsEntity.getAbout());
			details.setDetailsId(detailsEntity.getDetailsId());
			details.setHighlights(detailsEntity.getHighlights());
			details.setPace(detailsEntity.getPace());
			details.setPackageInclusion(detailsEntity.getPackageInclusion());

			ItineraryEntity itineraryEntity = destinations.getDestinationDetails().getItinerary();
			ItineraryDTO itinerary = new ItineraryDTO();

			itinerary.setFirstDay(itineraryEntity.getFirstDay());
			itinerary.setItineraryId(itineraryEntity.getItineraryId());
			itinerary.setLastDay(itineraryEntity.getLastDay());
			itinerary.setFirstDay(itineraryEntity.getFirstDay());
			itinerary.setRestOfDays(itineraryEntity.getRestOfDays());

			details.setItineraryDTO(itinerary);
			destination.setDestinationDetailsDTO(details);
		}
		return destination;
	}
	

}
