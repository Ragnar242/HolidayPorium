package com.tour.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.entity.BookingEntity;
import com.tour.entity.DestinationDetailsEntity;
import com.tour.entity.DestinationEntity;
import com.tour.entity.ItineraryEntity;
import com.tour.entity.UserEntity;
import com.tour.exception.HolidayPoriumException;
import com.tour.model.BookingDTO;
import com.tour.model.DestinationDTO;
import com.tour.model.DestinationDetailsDTO;
import com.tour.model.ItineraryDTO;
import com.tour.model.UserDTO;
import com.tour.repository.BookingRepository;

@Service("bookingService")
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;

	// This method fetches and returns the booking details by taking Booking object as parameter.
	@Override
	public BookingDTO bookPackage(BookingDTO booking) throws HolidayPoriumException {
		// TODO Auto-generated method stub
		DestinationEntity destinationEntity = bookingRepository
				.findByDestinationId(booking.getDestinationDTO().getDestinationId());
		UserEntity userEntity = bookingRepository.findByUserId(booking.getUserDTO().getUserId());

		if (destinationEntity != null && userEntity != null
				&& destinationEntity.getAvailability() >= booking.getNoOfPeople()) {

			BookingEntity bookingEntity = new BookingEntity();
			bookingEntity.setCheckIn(booking.getCheckIn());
			LocalDate date = booking.getCheckIn().plusDays(destinationEntity.getNoOfNights());

			bookingEntity.setCheckOut(date);

			bookingEntity.setNoOfPeople(booking.getNoOfPeople());

			bookingEntity.setTotalCost(booking.getTotalCost());
			;

			bookingEntity.setDestination(destinationEntity);
			bookingEntity.setUser(userEntity);

			bookingRepository.save(bookingEntity);

			Integer bookingId = bookingEntity.getBookingId();
			destinationEntity.setAvailability(destinationEntity.getAvailability() - booking.getNoOfPeople());

			booking.setBookingId(bookingId);

		} else {

			throw new HolidayPoriumException("BookingService.CANNOT_BOOK");

		}
		return booking;
	}

	// This method will view booking details as list considering userId from repository
	@Override
	public List<BookingDTO> bookingVision(Integer userId) throws HolidayPoriumException {
		// TODO Auto-generated method stub
		List<BookingDTO> bookingList = null;
		List<BookingEntity> bookingEntityList = bookingRepository.viewBooking(userId);

		if (bookingEntityList != null) {
			bookingList = new ArrayList<BookingDTO>();

			for (BookingEntity bookingEntity : bookingEntityList) {
				BookingDTO booking = new BookingDTO();
				booking.setBookingId(bookingEntity.getBookingId());
				booking.setCheckIn(bookingEntity.getCheckIn());
				booking.setCheckOut(bookingEntity.getCheckOut());
				booking.setNoOfPeople(bookingEntity.getNoOfPeople());
				booking.setTimeOfBooking(bookingEntity.getTimeOfBooking());
				booking.setTotalCost(bookingEntity.getTotalCost());

				DestinationEntity destinationEntity = bookingEntity.getDestination();
				DestinationDTO destination = new DestinationDTO();
				destination.setAvailability(destinationEntity.getAvailability());
				destination.setChargePerPerson(destinationEntity.getChargePerPerson());
				destination.setContinent(destinationEntity.getContinent());
				destination.setDestinationId(destinationEntity.getDestinationId());
				destination.setDestinationName(destinationEntity.getDestinationName());
				destination.setDiscount(destinationEntity.getDiscount());
				destination.setFlightCharge(destinationEntity.getFlightCharge());
				destination.setImageUrl(destinationEntity.getImageUrl());

				destination.setNoOfNights(destinationEntity.getNoOfNights());

				DestinationDetailsEntity detailsEntity = destinationEntity.getDestinationDetails();
				DestinationDetailsDTO details = new DestinationDetailsDTO();
				details.setAbout(detailsEntity.getAbout());
				details.setDetailsId(detailsEntity.getDetailsId());
				details.setHighlights(detailsEntity.getHighlights());
				details.setPace(detailsEntity.getPace());
				details.setPackageInclusion(detailsEntity.getPackageInclusion());
				ItineraryEntity itineraryEntity = detailsEntity.getItinerary();

				ItineraryDTO itinerary = new ItineraryDTO();
				itinerary.setFirstDay(itineraryEntity.getFirstDay());
				itinerary.setLastDay(itineraryEntity.getLastDay());
				itinerary.setItineraryId(itineraryEntity.getItineraryId());

				itinerary.setRestOfDays(itineraryEntity.getRestOfDays());

				details.setItineraryDTO(itinerary);

				destination.setDestinationDetailsDTO(details);
				UserEntity users = bookingEntity.getUser();
				UserDTO user = new UserDTO();
				
				user.setContactNumber(users.getContactNumber());
				user.setEmailId(users.getEmailId());
				user.setUserId(users.getUserId());
				user.setUserName(users.getUserName());
				user.setPassword(users.getPassword());

				booking.setDestinationDTO(destination);
				booking.setUserDTO(user);

				bookingList.add(booking);
			}
		} else {
			throw new HolidayPoriumException("BookingService.CANNOT_FIND_USER");
		}
		return bookingList;
	}

}
