package com.travel_agency.app.services.interfaces;

import java.util.List;

import com.travel_agency.app.models.Hotel;


public interface HotelServiceInterface {
	
	public Hotel save(Hotel hotel);
	
	public Hotel getById(Long id);
	
	public Hotel getByName(String name);
	
	public List<Hotel> getByCity(String city);
	
	public List<Hotel> getByPlacesAvailable(int places);
	
	public void delete(Long id);
}
