package com.travel_agency.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel_agency.app.exceptions.FlightNotFoundException;
import com.travel_agency.app.models.Flight;
import com.travel_agency.app.requests.FlightRequest;
import com.travel_agency.app.services.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@PostMapping
	public ResponseEntity<Flight> create(@Valid @RequestBody FlightRequest request) {
		Flight flight=new Flight();
		flight.setAllProperties(request);
		return ResponseEntity.created(null).body(this.flightService.save(flight));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Flight> getById(@PathVariable long id){
		Flight flight=this.flightService.getById(id);
		if(flight != null) {
			return ResponseEntity.ok(flight);
		}
		throw new FlightNotFoundException("Flight not found");
	}
	
	@GetMapping("/by-seats/{seats}")
	public ResponseEntity<List<Flight>> getBySeatsAvailable(@PathVariable int seats){
		return ResponseEntity.ok(this.flightService.getBySeatsAvailable(seats));
	}
	
	@GetMapping("/by-price/{price}")
	public ResponseEntity<List<Flight>> getByPrice(@PathVariable float price){
		return ResponseEntity.ok(this.flightService.getByPrice(price));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Flight> update(
			@PathVariable long id,
			@Valid @RequestBody FlightRequest request
	){
		Flight flight=this.flightService.getById(id);
		if(flight != null) {
			BeanUtils.copyProperties(request, flight);
			return ResponseEntity.ok(this.flightService.save(flight));
		}
		throw new FlightNotFoundException("Flight not found");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id){
		Flight flight=this.flightService.getById(id);
		if(flight != null) {
			this.flightService.delete(id);
			return ResponseEntity.noContent().build();
		}
		throw new FlightNotFoundException("Flight not found");
	}
}
