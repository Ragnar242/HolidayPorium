package com.tour.repository;

import org.springframework.data.repository.CrudRepository;

import com.tour.entity.DestinationEntity;

public interface DestinationDetailsRepository extends CrudRepository<DestinationEntity,Integer>{

	public DestinationEntity findDestinationDetailsByDestinationId(String destinationId) throws Exception;
	
}
