package com.tour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tour.model.DestinationDTO;
import com.tour.service.DestinationService;

@CrossOrigin
@RestController
@RequestMapping(value="DestinationAPI")
public class DestinationController {

	@Autowired
	DestinationService destinationService;
	
	@Autowired
	private Environment environment;
	
	
	//In this method we are fetching all the destination packages for the given continent.
	@GetMapping(value="/continentalPackages/{continent}")
	public ResponseEntity <List<DestinationDTO>> showContinentDetails(@PathVariable String continent) throws Exception  {
		try {
			List<DestinationDTO> destination = destinationService.browseDestinations(continent);	
	        ResponseEntity <List<DestinationDTO>> response = new ResponseEntity <List<DestinationDTO>>(destination, HttpStatus.OK);
		    return response;
	    }
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
		}	
	}
	
	@GetMapping(value="/allPackages")
	public ResponseEntity <List<DestinationDTO>> allDeals() throws Exception  {
		try {
		    List<DestinationDTO> destination = destinationService.dealsAvailable();	
		    ResponseEntity <List<DestinationDTO>> response = new ResponseEntity <List<DestinationDTO>>(destination, HttpStatus.OK);
		    return response;
	    }
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
		}	
	}
	
	@GetMapping(value="/hotDeals")
	public ResponseEntity <List<DestinationDTO>> hotDeals() throws Exception  {
		try {
		    List<DestinationDTO> destination = destinationService.hotDeals();
	        ResponseEntity <List<DestinationDTO>> response = new ResponseEntity <List<DestinationDTO>>(destination, HttpStatus.OK);
		    return response;
	    }
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
		}	
	}
}
