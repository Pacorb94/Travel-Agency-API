package com.travel_agency.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel_agency.app.exceptions.HotelNotFoundException;
import com.travel_agency.app.exceptions.NameAlreadyExistsException;
import com.travel_agency.app.models.Hotel;
import com.travel_agency.app.requests.HotelRequest;
import com.travel_agency.app.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> create(@Valid @RequestBody HotelRequest request){
		if(this.hotelService.getByName(request.getName()) == null) {
			Hotel hotel=new Hotel();
			hotel.setAllProperties(request);
			return ResponseEntity.created(null).body(this.hotelService.save(hotel));
		}
		throw new NameAlreadyExistsException("Name already exists");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getById(@PathVariable long id){
		Hotel hotel=this.hotelService.getById(id);
		if(hotel != null) {
			return ResponseEntity.ok(hotel);
		}
		throw new HotelNotFoundException("Hotel not found");
	}
	
	@GetMapping("/by-city/{city}")
	public ResponseEntity<List<Hotel>> getByCity(@PathVariable String city){
		return ResponseEntity.ok(this.hotelService.getByCity(city));	
	}
	
	@GetMapping("/by-places/{places}")
	public ResponseEntity<List<Hotel>> getByCity(@PathVariable int places){
		return ResponseEntity.ok(this.hotelService.getByPlacesAvailable(places));	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hotel> update(
			@PathVariable long id,
			@Valid @RequestBody HotelRequest request
	){
		Hotel hotel=this.hotelService.getById(id);
		if(hotel != null) {
			Hotel hotelAux=this.hotelService.getByName(request.getName());
			/*Comprobamos si el hotel con ese nombre ya existe y si el id 
			 es distinto al que vamos a modificar*/
			if(hotelAux != null && hotelAux.getId() != id) {
				throw new NameAlreadyExistsException("Name already exists");
			}
			hotel.setAllProperties(request);
			return ResponseEntity.ok(this.hotelService.save(hotel));	
		}
		throw new HotelNotFoundException("Hotel not found");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id){
		Hotel hotel=this.hotelService.getById(id);
		if(hotel != null) {
			this.hotelService.delete(id);
			return ResponseEntity.noContent().build();
		}
		throw new HotelNotFoundException("Hotel not found");
	}
}
