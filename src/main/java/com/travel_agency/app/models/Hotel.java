package com.travel_agency.app.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel_agency.app.requests.HotelRequest;


@Entity
@Table(name = "hotels")
public class Hotel implements Serializable {
	
	private static final long serialVersionUID = 2040341481521110171L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 45, nullable = false, unique = true)
	private String name;
	
	@Column(length = 45, nullable = false)
	private String city;

	@Column(length = 1, nullable = false)
	private int category;
	
	@Column(length = 10, nullable = false)
	private float price;
	
	@Column(length = 4, nullable = false)
	private int placesAvailable;
	
	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;
	
	public Hotel() {
		
	}
	
	public void setAllProperties(HotelRequest request) {
		this.setName(request.getName().trim());
		this.setCity(request.getCity().trim());
		this.setCategory(request.getCategory());
		this.setPlacesAvailable(request.getPlacesAvailable());
		//2 decimales
		this.setPrice((float) (Math.round(request.getPrice() * 100.0) / 100.0));
	}

	public long getId() {
		return id;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * M??todo que actualiza la propiedad updatedAt
	 */
	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt = new Date();
	}
}
