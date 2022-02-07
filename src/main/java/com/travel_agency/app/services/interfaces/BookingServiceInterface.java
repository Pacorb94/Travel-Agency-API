package com.travel_agency.app.services.interfaces;

import java.util.List;

import com.travel_agency.app.models.Booking;


public interface BookingServiceInterface {
	
	public Booking save(Booking booking);
	
	public List<Booking> getAll();
	
	public Booking getById(Long id);
	
	public List<Booking> getByDni(String dni);
	
	public void delete(Long id);
}
