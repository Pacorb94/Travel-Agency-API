package com.travel_agency.app.requests;

import javax.validation.constraints.*;

public class BookingRequest {
	
	@NotBlank(message = "dni is required")
	@Pattern(regexp = "^[0-9]{8}[a-zA-Z]{1}$", message = "dni must be 8 numbers and 1 word")
	private String dni;
	
	@NotNull(message = "clientsNumber is required")
	@Min(value = 1, message = "clientsNumber should not be less than 1")
	private int clientsNumber;
	
	@NotNull(message = "flightId is required")
	private int flightId;
	
	@NotNull(message = "hotelId is required")
	private int hotelId;
	
	public BookingRequest() {
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getClientsNumber() {
		return clientsNumber;
	}

	public void setClientsNumber(int clientsNumber) {
		this.clientsNumber = clientsNumber;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
}
