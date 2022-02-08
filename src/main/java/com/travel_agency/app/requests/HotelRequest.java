package com.travel_agency.app.requests;

import javax.validation.constraints.*;

public class HotelRequest {
	
	@NotBlank(message = "name is required")
	@Size(min = 1, max = 45, message = "name must be between 1 and 45 characters")
	private String name;
	
	@NotBlank(message = "city is required")
	@Size(min = 1, max = 45, message = "city must be between 1 and 45 characters")
	private String city;
	
	@NotNull(message = "category is required")
	@Min(value = 1, message = "category should not be less than 1")
	@Max(value = 5, message = "category should not be greater than 5")
	private int category;
	
	@NotNull(message = "price is required")
	@Min(value = 1, message = "price should not be less than 1")
	@Max(value = 1000000000, message = "price should not be greater than 1000000000")
	private float price;
	
	@NotNull(message = "placeAvailable is required")
	@Min(value = 1, message = "placeAvailable should not be less than 1")
	private int placesAvailable;
	
	public HotelRequest() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPlacesAvailable() {
		return placesAvailable;
	}

	public void setPlacesAvailable(int placesAvailable) {
		this.placesAvailable = placesAvailable;
	}
}
