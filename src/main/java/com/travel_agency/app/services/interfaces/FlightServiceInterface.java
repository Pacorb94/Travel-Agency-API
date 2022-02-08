package com.travel_agency.app.services.interfaces;

import java.util.List;

import com.travel_agency.app.models.Flight;


public interface FlightServiceInterface {
	
	public Flight save(Flight flight);
	
	public Flight getById(long id);
	
	public List<Flight> getBySeatsAvailable(int seatsAvailable);
	
	public List<Flight> getByPrice(float price);
	
	public void delete(long id);
}
