package com.tour.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

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

@SpringBootTest
public class BookingServiceTest {

		@Mock
		BookingRepository bookingRepository;

		@InjectMocks
		BookingServiceImpl bookingService;

		// checking for valid booking.
	@Test
		public void validGetBookPackage() throws Exception {

			BookingDTO book = new BookingDTO();
			book.setBookingId(1001);
			DestinationDTO dest = new DestinationDTO();
			dest.setDestinationId("D1001");
			dest.setAvailability(30);
			dest.setContinent("Europe");
			dest.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
			dest.setImageUrl("assets/geece.jpg");
			dest.setNoOfNights(7);
			dest.setFlightCharge((float) 500.0);
			dest.setChargePerPerson((float) 2499.0);
			dest.setDiscount((float) 0.0);

			DestinationDetailsDTO detail = new DestinationDetailsDTO();
			detail.setDetailsId("DL101");
			detail.setAbout(
					"Watch the setting sun from the hilltops of Greece’s most famous islands.Experience ancient history and open-air museums in the capital of Athens. Then, the quintessential, beautiful Greek islands you’ve been dreaming of come to life on the isles of Mykonos and Santorini.");
			detail.setPackageInclusion(
					"7 nights in handpicked hotels,7 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach");
			detail.setHighlights(
					"'Greece,Athens,Mykonos,Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus,Corinth Canal photo stop");
			detail.setPace(
					"On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails, with some hills and stairs.");

			ItineraryDTO itinerary = new ItineraryDTO();
			itinerary.setItineraryId("I1001");
			itinerary.setFirstDay("Travel day: Board your overnight flight to Athens");
			itinerary.setRestOfDays(
					"Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus");
			itinerary.setLastDay("Departure:Transfer to the airport for your flight home.");

			detail.setItineraryDTO(itinerary);

			dest.setDestinationDetailsDTO(detail);

			UserDTO user = new UserDTO();
			user.setUserId(101);
			user.setUserName("SCOTT");
			user.setEmailId("scott@stark.com");
			user.setContactNumber("8884967823");
			user.setPassword("Scott@123");
			book.setDestinationDTO(dest);
			book.setUserDTO(user);

			LocalDate indate = LocalDate.parse("2020-10-24");
			LocalDate outdate = LocalDate.parse("2020-10-31");
			LocalDateTime date = LocalDateTime.parse("2020-11-05T09:40:11");

			book.setCheckIn(indate);
			book.setCheckOut(outdate);
			book.setNoOfPeople(2);
			book.setTotalCost((float) 4356);
			book.setTimeOfBooking(date);

			BookingEntity exp = new BookingEntity();
			exp.setBookingId(1001);
			exp.setCheckIn(indate);
			exp.setCheckOut(outdate);
			exp.setNoOfPeople(2);

			exp.setTimeOfBooking(date);

			exp.setTotalCost((float) 4356);

			DestinationEntity destination = new DestinationEntity();
			destination.setDestinationId("D1001");
			destination.setContinent("Europe");
			destination.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
			destination.setImageUrl("assets/geece.jpg");
			destination.setNoOfNights(7);
			destination.setFlightCharge((float) 500.0);
			destination.setChargePerPerson((float) 2499.0);
			destination.setDiscount((float) 0.0);

			DestinationDetailsEntity details = new DestinationDetailsEntity();
			details.setDetailsId("DL101");
			detail.setAbout(
					"Watch the setting sun from the hilltops of Greece’s most famous islands.Experience ancient history and open-air museums in the capital of Athens. Then, the quintessential, beautiful Greek islands you’ve been dreaming of come to life on the isles of Mykonos and Santorini.");
			details.setPackageInclusion(
					"7 nights in handpicked hotels,7 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach");
			details.setHighlights(
					"'Greece,Athens,Mykonos,Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus,Corinth Canal photo stop");
			details.setPace(
					"On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails, with some hills and stairs.");

			ItineraryEntity itinerarys = new ItineraryEntity();
			itinerarys.setItineraryId("I1001");
			itinerarys.setFirstDay("Travel day: Board your overnight flight to Athens");
			itinerarys.setRestOfDays(
					"Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus");
			itinerarys.setLastDay("Departure:Transfer to the airport for your flight home.");
			details.setItinerary(itinerarys);
			destination.setDestinationDetails(details);

			UserEntity users = new UserEntity();
			users.setUserId(101);
			users.setUserName("SCOTT");
			users.setEmailId("scott@stark.com");
			users.setContactNumber("8884967823");
			users.setPassword("Scott@123");

			exp.setDestination(destination);
			exp.setUser(users);

			Mockito.when(bookingRepository.findByUserId(Mockito.anyInt())).thenReturn(exp.getUser());

			Assertions.assertEquals(book.getUserDTO().getUserId(), exp.getUser().getUserId());
		}

		// checking for invalid booking.
		@Test
		public void invalidGetBookPackage() throws Exception {

			BookingDTO book = new BookingDTO();
			book.setBookingId(1001);
			DestinationDTO dest = new DestinationDTO();
			dest.setDestinationId("D1001");
			dest.setAvailability(30);
			dest.setContinent("Europe");
			dest.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
			dest.setImageUrl("assets/geece.jpg");
			dest.setNoOfNights(7);
			dest.setFlightCharge((float) 500.0);
			dest.setChargePerPerson((float) 2499.0);
			dest.setDiscount((float) 0.0);

			DestinationDetailsDTO detail = new DestinationDetailsDTO();
			detail.setDetailsId("DL101");
			detail.setAbout(
					"Watch the setting sun from the hilltops of Greece’s most famous islands.Experience ancient history and open-air museums in the capital of Athens. Then, the quintessential, beautiful Greek islands you’ve been dreaming of come to life on the isles of Mykonos and Santorini.");
			detail.setPackageInclusion(
					"7 nights in handpicked hotels,7 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach");
			detail.setHighlights(
					"'Greece,Athens,Mykonos,Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus,Corinth Canal photo stop");
			detail.setPace(
					"On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails, with some hills and stairs.");

			ItineraryDTO itinerary = new ItineraryDTO();
			itinerary.setItineraryId("I1001");
			itinerary.setFirstDay("Travel day: Board your overnight flight to Athens");
			itinerary.setRestOfDays(
					"Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus");
			itinerary.setLastDay("Departure:Transfer to the airport for your flight home.");

			detail.setItineraryDTO(itinerary);

			dest.setDestinationDetailsDTO(detail);

			UserDTO user = new UserDTO();
			user.setUserId(901);
			user.setUserName("Zaid");
			user.setEmailId("scott@stark.com");
			user.setContactNumber("8884967823");
			user.setPassword("Scott@123");
			book.setDestinationDTO(dest);
			book.setUserDTO(user);

			LocalDate indate = LocalDate.parse("2020-10-24");
			LocalDate outdate = LocalDate.parse("2020-10-31");
			LocalDateTime date = LocalDateTime.parse("2020-11-05T09:40:11");

			book.setCheckIn(indate);
			book.setCheckOut(outdate);
			book.setNoOfPeople(2);
			book.setTotalCost((float) 4356);

			BookingEntity exp = new BookingEntity();
			exp.setBookingId(1001);
			exp.setCheckIn(indate);
			exp.setCheckOut(outdate);
			exp.setNoOfPeople(2);

			exp.setTimeOfBooking(date);

			exp.setTotalCost((float) 4356);

			DestinationEntity destination = new DestinationEntity();
			destination.setDestinationId("D1001");

			UserEntity users = new UserEntity();
			users.setUserId(101);

			exp.setDestination(destination);
			exp.setUser(users);

			Mockito.when(bookingRepository.findByUserId(Mockito.anyInt())).thenReturn(exp.getUser());
			HolidayPoriumException exception = Assertions.assertThrows(HolidayPoriumException.class,
					() -> bookingService.bookPackage(book));
			Assertions.assertEquals("BookingService.CANNOT_BOOK", exception.getMessage());

		}

		@Test
		public void validBookingByDestinationId()  {
			BookingDTO book = new BookingDTO();
			book.setBookingId(1001);
			DestinationDTO dest = new DestinationDTO();
			dest.setDestinationId("D1001");
			dest.setAvailability(30);
			dest.setContinent("Europe");
			dest.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
			dest.setImageUrl("assets/geece.jpg");
			dest.setNoOfNights(7);
			dest.setFlightCharge((float) 500.0);
			dest.setChargePerPerson((float) 2499.0);
			dest.setDiscount((float) 0.0);

			DestinationDetailsDTO detail = new DestinationDetailsDTO();
			detail.setDetailsId("DL101");
			detail.setAbout(
					"Watch the setting sun from the hilltops of Greece’s most famous islands.Experience ancient history and open-air museums in the capital of Athens. Then, the quintessential, beautiful Greek islands you’ve been dreaming of come to life on the isles of Mykonos and Santorini.");
			detail.setPackageInclusion(
					"7 nights in handpicked hotels,7 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach");
			detail.setHighlights(
					"'Greece,Athens,Mykonos,Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus,Corinth Canal photo stop");
			detail.setPace(
					"On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails, with some hills and stairs.");

			ItineraryDTO itinerary = new ItineraryDTO();
			itinerary.setItineraryId("I1001");
			itinerary.setFirstDay("Travel day: Board your overnight flight to Athens");
			itinerary.setRestOfDays(
					"Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus");
			itinerary.setLastDay("Departure:Transfer to the airport for your flight home.");
			detail.setItineraryDTO(itinerary);

			dest.setDestinationDetailsDTO(detail);

			UserDTO user = new UserDTO();
			user.setUserId(101);
			user.setUserName("SCOTT");
			user.setEmailId("scott@stark.com");
			user.setContactNumber("8884967823");
			user.setPassword("Scott@123");
			book.setDestinationDTO(dest);
			book.setUserDTO(user);

			LocalDate indate = LocalDate.parse("2020-10-24");
			LocalDate outdate = LocalDate.parse("2020-10-31");
			LocalDateTime date = LocalDateTime.parse("2020-11-05T09:40:11");

			book.setCheckIn(indate);
			book.setCheckOut(outdate);
			book.setNoOfPeople(2);
			book.setTotalCost((float) 4356);
			book.setTimeOfBooking(date);
			
			BookingEntity exp = new BookingEntity();
			exp.setBookingId(1001);
			exp.setCheckIn(indate);
			exp.setCheckOut(outdate);
			exp.setNoOfPeople(2);

			exp.setTimeOfBooking(date);

			exp.setTotalCost((float) 4356);

			DestinationEntity destination = new DestinationEntity();
			destination.setDestinationId("D1001");
			destination.setContinent("Europe");
			destination.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
			destination.setImageUrl("assets/geece.jpg");
			destination.setNoOfNights(7);
			destination.setFlightCharge((float) 500.0);
			destination.setChargePerPerson((float) 2499.0);
			destination.setDiscount((float) 0.0);

			DestinationDetailsEntity details = new DestinationDetailsEntity();
			details.setDetailsId("DL101");
			detail.setAbout(
					"Watch the setting sun from the hilltops of Greece’s most famous islands.Experience ancient history and open-air museums in the capital of Athens. Then, the quintessential, beautiful Greek islands you’ve been dreaming of come to life on the isles of Mykonos and Santorini.");
			details.setPackageInclusion(
					"7 nights in handpicked hotels,7 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach");
			details.setHighlights(
					"'Greece,Athens,Mykonos,Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus,Corinth Canal photo stop");
			details.setPace(
					"On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails, with some hills and stairs.");

			ItineraryEntity itinerarys = new ItineraryEntity();
			itinerarys.setItineraryId("I1001");
			itinerarys.setFirstDay("Travel day: Board your overnight flight to Athens");
			itinerarys.setRestOfDays(
					"Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus");
			itinerarys.setLastDay("Departure:Transfer to the airport for your flight home.");

			itinerarys.setLastDay("Departure:Transfer to the airport for your flight home.");
			details.setItinerary(itinerarys);
			destination.setDestinationDetails(details);

			UserEntity users = new UserEntity();
			users.setUserId(101);
			users.setUserName("SCOTT");
			users.setEmailId("scott@stark.com");
			users.setContactNumber("8884967823");
			users.setPassword("Scott@123");

			exp.setDestination(destination);
			exp.setUser(users);

			Mockito.when(bookingRepository.findByDestinationId(Mockito.anyString())).thenReturn(exp.getDestination());

			Assertions.assertEquals(book.getDestinationDTO().getDestinationId(), exp.getDestination().getDestinationId());

		}
		@Test
		public void validListOfBook() throws Exception{
			
			List<BookingDTO> bb = new ArrayList<BookingDTO>();
			List<BookingEntity> bList = new ArrayList<BookingEntity>();
			BookingDTO book = new BookingDTO();
			book.setBookingId(1001);
			DestinationDTO dest = new DestinationDTO();
			dest.setDestinationId("D1001");
			dest.setAvailability(30);
			dest.setContinent("Europe");
			dest.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
			dest.setImageUrl("assets/geece.jpg");
			dest.setNoOfNights(7);
			dest.setFlightCharge((float) 500.0);
			dest.setChargePerPerson((float) 2499.0);
			dest.setDiscount((float) 0.0);
			DestinationDetailsDTO detail = new DestinationDetailsDTO();
			detail.setDetailsId("DL101");
			detail.setAbout(
					"Watch the setting sun from the hilltops of Greece’s most famous islands.Experience ancient history and open-air museums in the capital of Athens. Then, the quintessential, beautiful Greek islands you’ve been dreaming of come to life on the isles of Mykonos and Santorini.");
			detail.setPackageInclusion(
					"7 nights in handpicked hotels,7 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach");
			detail.setHighlights(
					"'Greece,Athens,Mykonos,Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus,Corinth Canal photo stop");
			detail.setPace(
					"On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails, with some hills and stairs.");

			ItineraryDTO itinerary = new ItineraryDTO();
			itinerary.setItineraryId("I1001");
			itinerary.setFirstDay("Travel day: Board your overnight flight to Athens");
			itinerary.setRestOfDays(
					"Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus");
			itinerary.setLastDay("Departure:Transfer to the airport for your flight home.");
			
			detail.setItineraryDTO(itinerary);

			dest.setDestinationDetailsDTO(detail);
			UserDTO user = new UserDTO();
			user.setUserId(101);
			user.setUserName("SCOTT");
			user.setEmailId("scott@stark.com");
			user.setContactNumber("8884967823");
			user.setPassword("Scott@123");
			book.setDestinationDTO(dest);
			book.setUserDTO(user);
			LocalDate indate = LocalDate.parse("2020-10-24");
			LocalDate outdate = LocalDate.parse("2020-10-31");
			LocalDateTime date = LocalDateTime.parse("2020-11-05T09:40:11");

			book.setCheckIn(indate);
			book.setCheckOut(outdate);
			book.setNoOfPeople(2);
			book.setTotalCost((float) 4356);
			book.setTimeOfBooking(date);
			bb.add(book);
			BookingEntity exp = new BookingEntity();
			exp.setBookingId(1001);
			exp.setCheckIn(indate);
			exp.setCheckOut(outdate);
			exp.setNoOfPeople(2);

			exp.setTimeOfBooking(date);

			exp.setTotalCost((float) 4356);

			DestinationEntity destination = new DestinationEntity();
			destination.setDestinationId("D1001");
			destination.setContinent("Europe");
			destination.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
			destination.setImageUrl("assets/geece.jpg");
			destination.setNoOfNights(7);
			destination.setFlightCharge((float) 500.0);
			destination.setChargePerPerson((float) 2499.0);
			destination.setDiscount((float) 0.0);

			DestinationDetailsEntity details = new DestinationDetailsEntity();
			details.setDetailsId("DL101");
			detail.setAbout(
					"Watch the setting sun from the hilltops of Greece’s most famous islands.Experience ancient history and open-air museums in the capital of Athens. Then, the quintessential, beautiful Greek islands you’ve been dreaming of come to life on the isles of Mykonos and Santorini.");
			details.setPackageInclusion(
					"7 nights in handpicked hotels,7 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach");
			details.setHighlights(
					"'Greece,Athens,Mykonos,Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus,Corinth Canal photo stop");
			details.setPace(
					"On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails, with some hills and stairs.");

			ItineraryEntity itinerarys = new ItineraryEntity();
			itinerarys.setItineraryId("I1001");
			itinerarys.setFirstDay("Travel day: Board your overnight flight to Athens");
			itinerarys.setRestOfDays(
					"Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus");
			itinerarys.setLastDay("Departure:Transfer to the airport for your flight home.");

			itinerarys.setLastDay("Departure:Transfer to the airport for your flight home.");
			details.setItinerary(itinerarys);
			destination.setDestinationDetails(details);
			UserEntity users = new UserEntity();
			users.setUserId(101);
			users.setUserName("SCOTT");
			users.setEmailId("scott@stark.com");
			users.setContactNumber("8884967823");
			users.setPassword("Scott@123");

			exp.setDestination(destination);
			exp.setUser(users);
			bList.add(exp);
			
			Mockito.when(bookingRepository.viewBooking(Mockito.anyInt())).thenReturn(bList);

			Assertions.assertEquals(bb.get(0).getUserDTO().getUserId(),bList.get(0).getUser().getUserId());
			

			
			
		}
		
		@Test
		public void invalidList() throws Exception{
			
			
			
			
			List<BookingDTO> bb = new ArrayList<BookingDTO>();
			List<BookingEntity> bList = new ArrayList<BookingEntity>();
			BookingDTO book = new BookingDTO();
			book.setBookingId(1001);
			DestinationDTO dest = new DestinationDTO();
			dest.setDestinationId("D1001");
			dest.setAvailability(30);
			dest.setContinent("Europe");
			dest.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
			dest.setImageUrl("assets/geece.jpg");
			dest.setNoOfNights(7);
			dest.setFlightCharge((float) 500.0);
			dest.setChargePerPerson((float) 2499.0);
			dest.setDiscount((float) 0.0);
			DestinationDetailsDTO detail = new DestinationDetailsDTO();
			detail.setDetailsId("DL101");
			detail.setAbout(
					"Watch the setting sun from the hilltops of Greece’s most famous islands.Experience ancient history and open-air museums in the capital of Athens. Then, the quintessential, beautiful Greek islands you’ve been dreaming of come to life on the isles of Mykonos and Santorini.");
			detail.setPackageInclusion(
					"7 nights in handpicked hotels,7 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach");
			detail.setHighlights(
					"'Greece,Athens,Mykonos,Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus,Corinth Canal photo stop");
			detail.setPace(
					"On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails, with some hills and stairs.");

			
			ItineraryDTO itinerary = new ItineraryDTO();
			itinerary.setItineraryId("I1001");
			itinerary.setFirstDay("Travel day: Board your overnight flight to Athens");
			itinerary.setRestOfDays(
					"Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus");
			itinerary.setLastDay("Departure:Transfer to the airport for your flight home.");
			
			detail.setItineraryDTO(itinerary);

			dest.setDestinationDetailsDTO(detail);
			UserDTO user = new UserDTO();
			user.setUserId(120);
			user.setUserName("SCOTT");
			user.setEmailId("scott@stark.com");
			user.setContactNumber("8884967823");
			user.setPassword("Scott@123");
			book.setDestinationDTO(dest);
			book.setUserDTO(user);
			LocalDate indate = LocalDate.parse("2020-10-24");
			LocalDate outdate = LocalDate.parse("2020-10-31");
			LocalDateTime date = LocalDateTime.parse("2020-11-05T09:40:11");

			book.setCheckIn(indate);
			book.setCheckOut(outdate);
			book.setNoOfPeople(2);
			book.setTotalCost((float) 4356);
			book.setTimeOfBooking(date);
			bb.add(book);
			BookingEntity exp = new BookingEntity();
			exp.setBookingId(1001);
			exp.setCheckIn(indate);
			exp.setCheckOut(outdate);
			exp.setNoOfPeople(2);

			exp.setTimeOfBooking(date);

			exp.setTotalCost((float) 4356);

			DestinationEntity destination = new DestinationEntity();
			destination.setDestinationId("D1001");
			destination.setContinent("Europe");
			destination.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
			destination.setImageUrl("assets/geece.jpg");
			destination.setNoOfNights(7);
			destination.setFlightCharge((float) 500.0);
			destination.setChargePerPerson((float) 2499.0);
			destination.setDiscount((float) 0.0);

			DestinationDetailsEntity details = new DestinationDetailsEntity();
			details.setDetailsId("DL101");
			detail.setAbout(
					"Watch the setting sun from the hilltops of Greece’s most famous islands.Experience ancient history and open-air museums in the capital of Athens. Then, the quintessential, beautiful Greek islands you’ve been dreaming of come to life on the isles of Mykonos and Santorini.");
			details.setPackageInclusion(
					"7 nights in handpicked hotels,7 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach");
			details.setHighlights(
					"'Greece,Athens,Mykonos,Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus,Corinth Canal photo stop");
			details.setPace(
					"On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails, with some hills and stairs.");

			ItineraryEntity itinerarys = new ItineraryEntity();
			itinerarys.setItineraryId("I1001");
			itinerarys.setFirstDay("Travel day: Board your overnight flight to Athens");
			itinerarys.setRestOfDays(
					"Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus");
			itinerarys.setLastDay("Departure:Transfer to the airport for your flight home.");

			itinerarys.setLastDay("Departure:Transfer to the airport for your flight home.");
			details.setItinerary(itinerarys);
			destination.setDestinationDetails(details);
			UserEntity users = new UserEntity();
			users.setUserId(121);
			users.setUserName("SCOTT");
			users.setEmailId("scott@stark.com");
			users.setContactNumber("8884967823");
			users.setPassword("Scott@123");

			exp.setDestination(destination);
			exp.setUser(users);
			bList.add(exp);
			
			Mockito.when(bookingRepository.viewBooking(Mockito.anyInt())).thenReturn(bList);
		Exception exception = Assertions.assertThrows(Exception.class,
					() -> bookingService.bookPackage(book));
			Assertions.assertEquals("BookingService.CANNOT_BOOK", exception.getMessage());
			
		}
}
