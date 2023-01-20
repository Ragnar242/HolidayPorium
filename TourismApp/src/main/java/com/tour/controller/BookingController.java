package com.tour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tour.model.BookingDTO;
import com.tour.service.BookingService;

@CrossOrigin
@RestController
@RequestMapping("BookingAPI")
public class BookingController {

		@Autowired
		private BookingService bookingService;

		@Autowired
		private Environment environment;

		// This Post Mapping method is used to add booking object to database.
		@RequestMapping(value = "/packageBooking", method = RequestMethod.POST)
		public ResponseEntity<BookingDTO> registerUser(@RequestBody BookingDTO booking) throws Exception {
			try {
				BookingDTO bookingDetails = bookingService.bookPackage(booking);
				ResponseEntity<BookingDTO> response = new ResponseEntity<BookingDTO>(bookingDetails, HttpStatus.CREATED);
				return response;
			} catch (Exception e) {
				String message = environment.getProperty(e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message, e);

			}
		}

		// This method fetches the list of bookings of a user by passing user id as
		// parameter.
		@GetMapping(value = "/bookingVision/{userId}")
		public ResponseEntity<List<BookingDTO>> getViewBooking(@PathVariable Integer userId) throws Exception {
			try {
				List<BookingDTO> booking = bookingService.bookingVision(userId);

				ResponseEntity<List<BookingDTO>> response = new ResponseEntity<List<BookingDTO>>(booking, HttpStatus.OK);

				return response;
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
			}

		}
}
