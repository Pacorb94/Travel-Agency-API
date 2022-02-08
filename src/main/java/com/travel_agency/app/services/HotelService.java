package com.travel_agency.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel_agency.app.models.Hotel;
import com.travel_agency.app.repositories.HotelRepository;
import com.travel_agency.app.services.interfaces.HotelServiceInterface;

/**
 * Clase que separa la lógica del controlador y además esta clase sigue el
 * patrón facade
 */
@Service
public class HotelService implements HotelServiceInterface{

	@Autowired
	private HotelRepository hotelRepo;

	@Override
	@Transactional
	public Hotel save(Hotel hotel) {
		return this.hotelRepo.save(hotel);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Hotel getById(long id) {
		Optional<Hotel> hotel=this.hotelRepo.findById(id);
		if(hotel.isPresent()) {
			return hotel.get();
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Hotel getByName(String name) {
		Optional<Hotel> hotel=this.hotelRepo.findByName(name);
		if(hotel.isPresent()) {
			return hotel.get();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Hotel> getByCity(String city) {
		return this.hotelRepo.findByCity(city).get();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Hotel> getByPlacesAvailable(int places) {
		return this.hotelRepo.findByPlacesAvailable(places).get();
	}

	@Override
	@Transactional
	public void delete(long id) {
		this.hotelRepo.deleteById(id);	
	}
}
