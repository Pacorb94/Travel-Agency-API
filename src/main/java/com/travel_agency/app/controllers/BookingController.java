package com.travel_agency.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel_agency.app.exceptions.*;
import com.travel_agency.app.models.*;
import com.travel_agency.app.requests.BookingRequest;
import com.travel_agency.app.services.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private FlightService flightService;
	
	@Autowired 
	private HotelService hotelService;
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<Booking> create(@Valid @RequestBody BookingRequest request){
		Flight flight=this.flightService.getById(request.getFlightId());
		Hotel hotel=this.hotelService.getById(request.getHotelId());
		this.checkFlightAndHotelExists(flight, hotel);	
		this.checkSeatsAndPlacesAvailable(flight, hotel, request);	
		this.updateSeatsAndPlaces(flight, hotel, request);
		Booking booking=new Booking();
		booking.setAllProperties(request, flight, hotel);
		return ResponseEntity.created(null).body(this.bookingService.save(booking));
	}
	
	private void checkFlightAndHotelExists(Flight flight, Hotel hotel) {
		if(flight == null) {
			throw new FlightNotFoundException("Flight not found");
		}
		if(hotel == null) {
			throw new HotelNotFoundException("Hotel not found");
		}
	}
	
	private void checkSeatsAndPlacesAvailable(Flight flight, Hotel hotel, BookingRequest request) {
		//Si hay menos asientos disponibles
		if(flight.getSeatsAvailable()<request.getClientsNumber()) {
			throw new NoSeatsAvailableException("No seats available for this flight");
		}
		//Si hay menos plazas disponibles
		if(hotel.getPlacesAvailable()<request.getClientsNumber()) {
			throw new NoPlacesAvailableException("No places available for this hotel");
		}
	}
	
	private void updateSeatsAndPlaces(Flight flight, Hotel hotel, BookingRequest request) {
		flight.setSeatsAvailable(flight.getSeatsAvailable()-request.getClientsNumber());
		this.flightService.save(flight);
		hotel.setPlacesAvailable(hotel.getPlacesAvailable()-request.getClientsNumber());
		this.hotelService.save(hotel);
	}
	
	@GetMapping
	public ResponseEntity<List<Booking>> getAll(){
		return ResponseEntity.ok(this.bookingService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Booking> getById(@PathVariable long id){
		Booking booking=this.bookingService.getById(id);
		if(booking != null) {
			return ResponseEntity.ok(booking);
		}
		throw new BookingNotFoundException("Booking not found");
	}
	
	@GetMapping("by-dni/{dni}")
	public ResponseEntity<List<Booking>> getByDni(@PathVariable String dni){
		return ResponseEntity.ok(this.bookingService.getByDni(dni));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id){
		Booking booking=this.bookingService.getById(id);
		if(booking != null) {
			this.bookingService.delete(id);
			return ResponseEntity.noContent().build();
		}
		throw new BookingNotFoundException("Booking not found");
	}
}