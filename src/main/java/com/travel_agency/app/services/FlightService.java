package com.travel_agency.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel_agency.app.models.Flight;
import com.travel_agency.app.repositories.FlightRepository;
import com.travel_agency.app.services.interfaces.FlightServiceInterface;


/**
 * Clase que separa la lógica del controlador y además esta clase sigue el
 * patrón facade
 */
@Service
public class FlightService implements FlightServiceInterface{

	@Autowired
	private FlightRepository flightRepo;
	
	@Override
	@Transactional
	public Flight save(Flight flight) {
		return this.flightRepo.save(flight);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Flight> getAll() {
		return this.flightRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Flight getById(Long id) {
		Optional<Flight> flight=this.flightRepo.findById(id);
		if(flight.isPresent()) {
			return flight.get();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Flight> getBySeatsAvailable(int seatsAvailable) {
		return this.flightRepo.findBySeatsAvailable(seatsAvailable).get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Flight> getByPrice(float price) {
		return this.flightRepo.findByPrice(price).get();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.flightRepo.deleteById(id);	
	}
}
