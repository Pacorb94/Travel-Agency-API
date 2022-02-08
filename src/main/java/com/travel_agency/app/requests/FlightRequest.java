package com.travel_agency.app.requests;

import javax.validation.constraints.*;

public class FlightRequest {
	
	@NotBlank(message = "company is required")
	@Size(min = 1, max = 45, message = "company must be between 1 and 45 characters")
	private String company;
	
	@NotNull(message = "price is required")
	@Min(value = 1, message = "price should not be less than 1")
	@Max(value = 10, message = "price should not be greater than 10")
	private float price;
	
	@NotNull(message = "seatsAvailable is required")
	@Min(value = 100, message = "seatsAvailable should not be less than 100")
	@Max(value = 350, message = "seatsAvailable should not be greater than 350")
	private int seatsAvailable;
	
	@NotBlank(message = "origin is required")
	@Size(min = 1, max = 45, message = "origin must be between 1 and 45 characters")
	private String origin;
	
	@NotBlank(message = "destination is required")
	@Size(min = 1, max = 45, message = "destination must be between 1 and 45 characters")
	private String destination;
	
	public FlightRequest() {
		
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
