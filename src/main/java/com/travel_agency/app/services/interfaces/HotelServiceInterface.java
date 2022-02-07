package com.travel_agency.app.services.interfaces;

import java.util.List;

import com.travel_agency.app.models.Hotel;


public interface HotelServiceInterface {
	
	public Hotel save(Hotel hotel);
	
	public List<Hotel> getAll();
	
	public Hotel getById(Long id);
	
	public List<Hotel> getByCity(String city);
	
	public List<Hotel> getByPlacesAvailable(int placesAvailable);
	
	public List<Hotel> getByCategory(int category);
	
	public void delete(Long id);
}
