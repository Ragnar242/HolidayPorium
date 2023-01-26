package com.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tour.entity.DestinationEntity;

public interface DestinationRepository extends CrudRepository<DestinationEntity, Integer> {

		@Query("select d from Destination d where Lower(d.continent)=?1")
		List<DestinationEntity> findDestinationByContinent(String continent);

		@Query("select d from Destination d")
		List<DestinationEntity> findDestination();
		
		@Query("select d from Destination d where discount>0.0")
		List<DestinationEntity> findHotDeals();

}
