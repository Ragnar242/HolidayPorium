package com.tour.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookingRepositoryTest {

		@Autowired
		BookingRepository bookingRepository;
		
		
		//Valid booking for valid destination id
		@Test
			public void validBooking() throws Exception{
				Assertions.assertNotNull(bookingRepository.findByDestinationId("D1001"));
		}
		
		//valid booking by invaild user id
		@Test
		public void invalidBooking() throws Exception{
			Assertions.assertNull(bookingRepository.findByDestinationId("D1080"));
		}
		
		//valid booking by valid user id
		@Test
		public void validUserBooking() throws Exception{
			Assertions.assertFalse(bookingRepository.viewBooking(101).isEmpty());
		}

}
