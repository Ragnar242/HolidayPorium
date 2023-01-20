package com.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tour.entity.BookingEntity;
import com.tour.entity.DestinationEntity;
import com.tour.entity.UserEntity;

public interface BookingRepository extends CrudRepository<BookingEntity, Integer>{

	@Query("SELECT b.destination FROM Booking b WHERE b.destination.destinationId=?1")
	public DestinationEntity findByDestinationId(String destinationId);
	@Query("SELECT b.user FROM Booking b WHERE b.user.userId=?1")
	public UserEntity findByUserId(Integer userId);

	@Query("SELECT b FROM Booking b WHERE b.user.userId=?1")
	public List<BookingEntity> viewBooking(Integer userId);
	
}
