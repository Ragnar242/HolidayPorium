package com.tour.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.entity.DestinationEntity;
import com.tour.exception.HolidayPoriumException;
import com.tour.model.DestinationDTO;
import com.tour.model.DestinationDetailsDTO;
import com.tour.model.ItineraryDTO;
import com.tour.repository.DestinationRepository;

import jakarta.transaction.Transactional;

@Service(value = "destinationService")
@Transactional
public class DestinationServiceImpl implements DestinationService{

	@Autowired
	private DestinationRepository destinationRepository;
	
	//This method fetches and returns the list of destinations by accepting continent as parameter.
	@Override
	public List<DestinationDTO> browseDestinations(String continent) throws HolidayPoriumException {
		// TODO Auto-generated method stub
		List<DestinationDTO> lists=null;
		List<DestinationEntity> destinations=destinationRepository.findDestinationByContinent(continent);
		if(destinations.isEmpty()){
	        throw new HolidayPoriumException("DestinationService.HAVING_NO_DESTINATION");
		}
		else {
			
			lists=new ArrayList<DestinationDTO>();
			for(DestinationEntity e:destinations) {
				
				DestinationDTO dest=new DestinationDTO();
				dest.setAvailability(e.getAvailability());
				dest.setDestinationId(e.getDestinationId());
				dest.setContinent(e.getContinent());
				dest.setChargePerPerson(e.getChargePerPerson());
				dest.setDestinationName(e.getDestinationName());
				dest.setDiscount(e.getDiscount());
				dest.setFlightCharge(e.getFlightCharge());
				dest.setImageUrl(e.getImageUrl());
				dest.setNoOfNights(e.getNoOfNights());
					DestinationDetailsDTO details=new DestinationDetailsDTO();
					details.setDetailsId(e.getDestinationDetails().getDetailsId());
					details.setAbout(e.getDestinationDetails().getAbout());
					details.setHighlights(e.getDestinationDetails().getHighlights());
					details.setPace(e.getDestinationDetails().getPace());
					details.setPackageInclusion(e.getDestinationDetails().getPackageInclusion());
						ItineraryDTO itinerary=new ItineraryDTO();
						itinerary.setFirstDay(e.getDestinationDetails().getItinerary().getFirstDay());
						itinerary.setItineraryId(e.getDestinationDetails().getItinerary().getItineraryId());
						itinerary.setLastDay(e.getDestinationDetails().getItinerary().getLastDay());
						itinerary.setRestOfDays(e.getDestinationDetails().getItinerary().getRestOfDays());
					details.setItineraryDTO(itinerary);
				dest.setDestinationDetailsDTO(details);
				lists.add(dest);
			}
		}
		return lists;
	}

	//This method returns the list of destinations.
	@Override
	public List<DestinationDTO> dealsAvailable() throws HolidayPoriumException {
		// TODO Auto-generated method stub
		List<DestinationDTO> lists = new ArrayList<DestinationDTO>();
		  List<DestinationEntity> destinations=destinationRepository.findDestination();
		  if(destinations.isEmpty()) { throw new
			  HolidayPoriumException("DestinationService.NO_PACKAGE"); 
		  }
		  else
			{
				for(DestinationEntity e:destinations) {
					
					DestinationDTO dest = new DestinationDTO();
					dest.setAvailability(e.getAvailability());
					dest.setDestinationId(e.getDestinationId());
					dest.setContinent(e.getContinent());
					dest.setChargePerPerson(e.getChargePerPerson());
					dest.setDestinationName(e.getDestinationName());
					dest.setDiscount(e.getDiscount());
					dest.setFlightCharge(e.getFlightCharge());
					dest.setImageUrl(e.getImageUrl());
					dest.setNoOfNights(e.getNoOfNights());
						DestinationDetailsDTO details=new DestinationDetailsDTO();
						details.setDetailsId(e.getDestinationDetails().getDetailsId());
						details.setAbout(e.getDestinationDetails().getAbout());
						details.setHighlights(e.getDestinationDetails().getHighlights());
						details.setPace(e.getDestinationDetails().getPace());
						details.setPackageInclusion(e.getDestinationDetails().getPackageInclusion());
							ItineraryDTO itinerary=new ItineraryDTO();
							itinerary.setFirstDay(e.getDestinationDetails().getItinerary().getFirstDay());
							itinerary.setItineraryId(e.getDestinationDetails().getItinerary().getItineraryId());
							itinerary.setLastDay(e.getDestinationDetails().getItinerary().getLastDay());
							itinerary.setRestOfDays(e.getDestinationDetails().getItinerary().getRestOfDays());
							details.setItineraryDTO(itinerary);
					dest.setDestinationDetailsDTO(details);
				lists.add(dest);
					
				}	
			}
			return lists;
	}

	@Override
	public List<DestinationDTO> hotDeals() throws HolidayPoriumException {
		// TODO Auto-generated method stub
		List<DestinationEntity> listEntity=destinationRepository.findHotDeals();
		List<DestinationDTO> listDes=null;
		if(!listEntity.isEmpty())
		{
			listDes=new ArrayList<DestinationDTO>();
			for(DestinationEntity de:listEntity)
			{
				DestinationDTO destination=new DestinationDTO();
				destination.setDestinationId(de.getDestinationId());
				destination.setContinent(de.getContinent());
				destination.setDestinationName(de.getDestinationName());
				destination.setImageUrl(de.getImageUrl());
				destination.setNoOfNights(de.getNoOfNights());
				destination.setFlightCharge(de.getFlightCharge());
				destination.setChargePerPerson(de.getChargePerPerson());
				destination.setDiscount(de.getDiscount());
				destination.setAvailability(de.getAvailability());
				 
				DestinationDetailsDTO details=new DestinationDetailsDTO();
				details.setDetailsId(de.getDestinationDetails().getDetailsId());
				details.setAbout(de.getDestinationDetails().getAbout());
				details.setHighlights(de.getDestinationDetails().getHighlights());
				details.setPace(de.getDestinationDetails().getPace());
				details.setPackageInclusion(de.getDestinationDetails().getPackageInclusion());
				
				ItineraryDTO itinerary=new ItineraryDTO();
				itinerary.setFirstDay(de.getDestinationDetails().getItinerary().getFirstDay());
				itinerary.setItineraryId(de.getDestinationDetails().getItinerary().getItineraryId());
				itinerary.setLastDay(de.getDestinationDetails().getItinerary().getLastDay());
				itinerary.setRestOfDays(de.getDestinationDetails().getItinerary().getRestOfDays());
				
				details.setItineraryDTO(itinerary);
				destination.setDestinationDetailsDTO(details);
				listDes.add(destination);
				
			}
		}
		else {
			throw new HolidayPoriumException("DestinationService.NO_SPECIALPACKAGE");
		}
		return listDes;
	}

}
